package com.example.mvvmdemo.bindingutils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmdemo.R
import com.example.mvvmdemo.RoomUtil.DatabaseActivity
import com.example.mvvmdemo.databinding.BindingAdapterActivityBinding
import com.example.mvvmdemo.retrofitmvvm.ui.LoginActivity

class ActivityBindingAdapter: AppCompatActivity() {

    lateinit var bindingAdapterActivity: BindingAdapterActivityBinding
    lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAdapterActivity = DataBindingUtil.setContentView(this, R.layout.binding_adapter_activity)

        val post = Post("Title Hello", "Description Hi", "https://picsum.photos/200")
        bindingAdapterActivity.post = post

        bindingAdapterActivity.mButtonDb.setOnClickListener {
            startActivity(Intent(this, DatabaseActivity::class.java))
        }
        bindingAdapterActivity.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        println("Hello")
    }
}