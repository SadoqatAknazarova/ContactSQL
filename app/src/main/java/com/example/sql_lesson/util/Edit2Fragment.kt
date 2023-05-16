package com.example.sql_lesson.util

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sql_lesson.DBHelper
import com.example.sql_lesson.R
import com.example.sql_lesson.databinding.FragmentEdit2Binding
import com.example.sql_lesson.model.contact.adapter.DataUser

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Edit2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Edit2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val binding=FragmentEdit2Binding.inflate(inflater,container,false)
        val contact = arguments?.getSerializable("contacttt") as DataUser
        binding.settextName.setText(contact.name_dataClass)
        binding.settextPhone.setText(contact.contact_dataClass)
val myObject=DBHelper(requireContext())



binding.saveEdit.setOnClickListener {
    myObject.editContact(
        DataUser(
            id=contact.id,
            name_dataClass = binding.settextName.text.toString(),
            contact_dataClass = binding.settextPhone.text.toString()

        ))

}
        binding.backIdedit.setOnClickListener {
            findNavController().navigate(R.id.action_edit2Fragment_to_editFragment)
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Edit2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Edit2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}