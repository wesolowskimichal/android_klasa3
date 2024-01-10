package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ContactsAdapter
import com.example.myapplication.models.Contact
import com.example.myapplication.ui.viewmodels.MainViewModel
import com.example.myapplication.ui.viewmodels.MainViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var contactsAdapter: ContactsAdapter
    lateinit var smsManager: SmsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModelProvider = MainViewModelProvider(application)
        mainViewModel = ViewModelProvider(this, mainViewModelProvider)[MainViewModel::class.java]
        smsManager = SmsManager.getDefault()
        setContentView(R.layout.activity_main)

        contactsAdapter = ContactsAdapter()

        val rv = findViewById<RecyclerView>(R.id.rvContacts)
        val btn = findViewById<Button>(R.id.btnSendSms)
        val et = findViewById<EditText>(R.id.etSmsMess)

        rv.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(context)

        }

        mainViewModel.getLocalContact().observe(this, Observer {contacts ->
            contactsAdapter.differ.submitList(contacts)
        })

        contactsAdapter.setOnItemClickListener {contact ->
            contact.selected = !contact.selected
            contactsAdapter.notifyItemChanged(contactsAdapter.differ.currentList.indexOf(contact))
        }

        btn.setOnClickListener {
            val message = et.text.toString()
            sendSms(contactsAdapter.differ.currentList, message)
        }
    }

    private fun sendSms(contacts: List<Contact>, message: String) {
        for(contact in contacts) {
            if(contact.selected) {
                smsManager.sendTextMessage(contact.number, null, message, null, null)
                Log.d("SENDING", "${contact.toString()}: ${message}")
            }
        }
    }
}