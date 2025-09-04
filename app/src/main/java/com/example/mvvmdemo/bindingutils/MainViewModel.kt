package com.example.mvvmdemo.bindingutils

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var counter: Int = 0

    fun increment(){
        counter++
    }
}