package com.example.mvvmdemo.RoomUtil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DatabaseViewModelFactory(private val contactRepository: ContactRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DatabaseViewModel(contactRepository) as T
    }

}