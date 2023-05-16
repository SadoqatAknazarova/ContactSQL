package com.example.sql_lesson.model.contact

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sql_lesson.R
import com.example.sql_lesson.databinding.FragmentAddBinding
import com.example.sql_lesson.DBHelper
import com.example.sql_lesson.model.contact.adapter.DataUser

import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var name: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var save: Button
    private lateinit var db: DBHelper
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
        var binding =FragmentAddBinding.inflate(inflater,container,false)
        val myDbject = DBHelper(requireContext())
        binding.backId.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_contactFragment)
        }
        binding.saveId.setOnClickListener {

            if(binding.editTextNameId.text.toString().isNotEmpty() || binding.editTextPhoneId.text.toString().isNotEmpty()){
                myDbject.addContact(
                    DataUser(
                    name_dataClass = binding.editTextNameId.text.toString(),
                    contact_dataClass = binding.editTextPhoneId.text.toString()

                    )
                )

                Toast.makeText(this.requireContext(), "Contact Added", Toast.LENGTH_SHORT).show()
            }


        }
        return binding.root
    }


}