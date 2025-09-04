package com.example.mvvmdemo.RoomUtil

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: ContactRepository) : ViewModel() {

    fun getContacts(): LiveData<List<Contact>>{
        return repository.getContacts()
    }

     fun insertContacts(contact: Contact){
         viewModelScope.launch(Dispatchers.IO) {
              repository.insertContact(contact)
         }
    }
}