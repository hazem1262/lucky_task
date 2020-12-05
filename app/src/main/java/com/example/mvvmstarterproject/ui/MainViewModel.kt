package com.example.mvvmstarterproject.test

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mvvmstarterproject.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var testString: String
    init {
        Log.d("test", "view model init")
    }
    fun printTestString(){
        Log.d("test", "view model init $testString")
    }
}