package com.example.mvvmdemo.RoomUtil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.ActivityDatabaseBinding
import com.example.mvvmdemo.retrofitmvvm.ui.LoginActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit

class DatabaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDatabaseBinding
    lateinit var databaseViewModel: DatabaseViewModel
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_database)

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val notificationWorkRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(notificationWorkRequest)*/

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Jaydeep", "9876543210", Date(),1))
        }

        val dao = ContactDatabase.getDatabase(applicationContext).contactDao()
        val repository = ContactRepository(dao)

        databaseViewModel = ViewModelProvider(this,DatabaseViewModelFactory(repository))[DatabaseViewModel::class.java]


        databaseViewModel.getContacts().observe(this, Observer {
            binding.contacts = it.toString()
        })

        binding.buttonAddContact.setOnClickListener {
            val contact = Contact(0, "Jaydeep", "9876543210", Date(),1)
            databaseViewModel.insertContacts(contact)

        }

    }


    fun getData(view: View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("Jaydeep", it.toString())
        })
    }
}