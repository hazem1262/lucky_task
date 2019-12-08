package com.example.mvvmstarterproject.test

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var testString: String
    init {
        Log.d("test", "view model init")
    }
    fun printTestString(){
        Log.d("test", "view model init $testString")
    }
}