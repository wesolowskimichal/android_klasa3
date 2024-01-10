package com.example.myapplication.ui.viewmodels

import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Contact

class MainViewModel(
    val application: Application
): ViewModel() {
    var _localContacts: MutableLiveData<List<Contact>> = MutableLiveData()

    fun getLocalContact(): LiveData<List<Contact>> {
        _localContacts.postValue(getContacts())
        return _localContacts
    }

    private fun getContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val contentResolver = application.applicationContext.contentResolver
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val nameIndex =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberIndex =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                val name = it.getString(nameIndex)
                val number = it.getString(numberIndex)

                val contact = Contact(name = name, number = number, selected = false)
                contacts.add(contact)
            }
        }

        cursor?.close()

        return contacts
    }
}