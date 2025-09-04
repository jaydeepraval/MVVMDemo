package com.example.mvvmdemo.RoomUtil

import androidx.lifecycle.LiveData

class ContactRepository(val contactDao: ContactDAO) {

    fun getContacts(): LiveData<List<Contact>>{
        return contactDao.getContact()
    }

    suspend fun insertContact(contact: Contact){
        contactDao.insertContact(contact)
    }
}