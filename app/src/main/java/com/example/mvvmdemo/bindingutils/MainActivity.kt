package com.example.mvvmdemo.bindingutils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    lateinit var mTVLabel: TextView
  //  lateinit var mButtonClick: Button
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.mTVLabel.text = mainViewModel.counter.toString()

        binding.mButtonClick.setOnClickListener {
            mainViewModel.increment()
            binding.mTVLabel.text = mainViewModel.counter.toString()
        }
    }
}