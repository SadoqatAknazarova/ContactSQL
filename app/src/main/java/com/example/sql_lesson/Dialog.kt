package com.example.contactsapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.sql_lesson.DBHelper


import com.example.sql_lesson.model.contact.adapter.DataUser

class Dialog(var contact: DataUser) : DialogFragment() {
    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Delete contact")
                .setMessage("Are you sure you want to delete this contact")
                .setCancelable(true)
                .setPositiveButton("Yes") { _, _ ->
                    DBHelper(requireContext()).deleteContact(contact = contact)
                    findNavController().popBackStack()
                }
                .setNegativeButton("No") { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}