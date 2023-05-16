package com.example.sql_lesson.util

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.Dialog
import com.example.sql_lesson.R

import com.example.sql_lesson.databinding.FragmentEditBinding

import com.example.sql_lesson.model.contact.adapter.DataUser

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
    // TODO: Rename and change types of parameters

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding= FragmentEditBinding.inflate(inflater, container, false)
        val contact = arguments?.getSerializable("contactt") as DataUser

        binding.nameEditId.text = contact.name_dataClass
        binding.phoneNumberEditId.text = contact.contact_dataClass

        binding.delete.setOnClickListener {
            Dialog(contact).show(parentFragmentManager,"myDialog")
        }

        binding.edit.setOnClickListener {
            val bundle = bundleOf("contacttt" to contact)
            findNavController().navigate(
                R.id.action_editFragment_to_edit2Fragment,
                bundle
            )

        }

        val phone=binding.phoneNumberEditId.text.toString()

      binding.call.setOnClickListener {
       val callIntent=Intent(Intent.ACTION_CALL)
       callIntent.data= Uri.parse("tel:$phone")
       startActivity(callIntent)
        }



        binding.backEdit.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_contactFragment)
        }


        return binding.root
    }
//private fun checkPermissions(){
//    if (ActivityCompat.checkSelfPermission(context,Manifest.permission))
//}



}