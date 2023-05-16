package com.example.sql_lesson.model.contact

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.DeleteAllDialog
import com.example.sql_lesson.R
import com.example.sql_lesson.databinding.FragmentContactBinding
import com.example.sql_lesson.AdapterClass
import com.example.sql_lesson.DBHelper
import com.example.sql_lesson.model.contact.adapter.DataUser

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var list:MutableList<DataUser>
class ContactFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var db_h: DBHelper
    private lateinit var new_list:MutableList<DataUser>
    private lateinit var adapter_class: AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =FragmentContactBinding.inflate(inflater,container,false)
        binding.floatButton.setOnClickListener{
            findNavController().navigate(R.id.action_contactFragment_to_addFragment)
        }
//        db_h= this.context?.let { DBHelper(it) }!!
//        recyclerView=binding.recyclerView
//        recyclerView.layoutManager= LinearLayoutManager(this.context)
//        recyclerView.setHasFixedSize(true)
//

        var contact: DataUser



        var mydb=DBHelper(requireContext())
        var list=mydb.getContacts()
        var adapter = AdapterClass(
            list, object : AdapterClass.ContactInterface {
                override fun onClick(contact: DataUser) {

                    val bundle = bundleOf("contactt" to contact)
                    findNavController().navigate(
                        R.id.action_contactFragment_to_editFragment,
                        bundle
                    )
                }
            })
binding.recyclerView.adapter=adapter

        binding.search.addTextChangedListener {
            var filter = mutableListOf<DataUser>()
            if (it != null) {
                for (c in list) {
                    if (c.name_dataClass.toUpperCase().contains(it.toString().toUpperCase())) {
                        filter.add(c)
                    }
                }
        }

     adapter= AdapterClass(
         filter, object : AdapterClass.ContactInterface {
             override fun onClick(contact: DataUser) {

                 val bundle = bundleOf("contactt" to contact)
                 findNavController().navigate(
                     R.id.action_contactFragment_to_editFragment,
                     bundle
                 )
             }
         })
            binding.recyclerView.adapter=adapter
            //  binding.burgerRv.adapter = adapter
                }


//
//        binding.se.doOnTextChanged { text, start, before, count ->
//            if (count == 0) {
//                binding.search.visibility = View.GONE
//                binding.toolbar.visibility = View.VISIBLE
//                binding.search.clearFocus()
//                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(binding.search.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
//
//                binding.contactRv.adapter = allContactsAdapter()
//            } else {
//                var filteredContacts: MutableList<Contact> = myDb.getContactsFilteredByName(text.toString())
//                var adapter = ContactAdapter(filteredContacts, object : ContactAdapter.ContactInterface {
//                    override fun onClick(contact: Contact) {
//                        val bundle = bundleOf()
//                        bundle.putSerializable("contact", contact)
//                        findNavController().navigate(R.id.action_contactsFragment_to_viewFragment, bundle)
//                    }
//                })
//                binding.contactRv.adapter = adapter
//            }
//        }



        binding.toolbar2.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.searchh -> {
                   binding.search.visibility = View.VISIBLE
                  binding.text.visibility=View.GONE
                    binding.search.isFocusable = true
                    binding.search.requestFocus()

                    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(binding.search, InputMethodManager.SHOW_IMPLICIT)

                    true
                }
                R.id.az -> {
                    list.sortBy {it.name_dataClass}
                    adapter = AdapterClass(
                        list, object : AdapterClass.ContactInterface {
                            override fun onClick(contact: DataUser) {

                                val bundle = bundleOf("contactt" to contact)
                                findNavController().navigate(
                                    R.id.action_contactFragment_to_editFragment,
                                    bundle
                                )
                            }
                        })

                    binding.recyclerView.adapter = adapter
                    true
                }

                R.id.za -> {
                    list.sortByDescending { it.name_dataClass }
                    adapter = AdapterClass(
                        list, object : AdapterClass.ContactInterface {
                            override fun onClick(contact: DataUser) {

                                val bundle = bundleOf("contactt" to contact)
                                findNavController().navigate(
                                    R.id.action_contactFragment_to_editFragment,
                                    bundle
                                )
                            }
                        })
                    binding.recyclerView.adapter = adapter
                    true
                }
                R.id.delete -> {
                    DeleteAllDialog().show(parentFragmentManager,"myDeleteAllDialog")
                    binding.recyclerView.adapter = allContactsAdapter()
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    private fun allContactsAdapter(isAz: Boolean = true): AdapterClass {
        return AdapterClass(DBHelper(requireContext()).getContacts(), object : AdapterClass.ContactInterface {
            override fun onClick(contact: DataUser) {
                val bundle = bundleOf()
                bundle.putSerializable("contact", contact)
                findNavController().navigate(R.id.action_contactFragment_to_editFragment, bundle)
            }
        })



    }

//    private fun load(){
//        var newcursor: Cursor? =db_h.getString()
//        new_list= mutableListOf()
//        while (newcursor!!.moveToNext()){
//            val name=newcursor.getString(0)
//            val number=newcursor.getString(1)
//            new_list.add(DataUser(name,number))
//        }
//        adapter_class = AdapterClass(new_list)
//        recyclerView.adapter=adapter_class
//    }
//











//    val myDb = DBHelper(requireContext())
//    contacts = myDb.getContacts()
//
//    if (contacts.isEmpty()) {
//        binding.box.visibility = View.VISIBLE
//    } else {
//        binding.contactRv.adapter = allContactsAdapter()
//    }
//



}