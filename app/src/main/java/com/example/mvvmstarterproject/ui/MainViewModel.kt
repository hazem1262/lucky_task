package com.example.mvvmstarterproject.ui

import android.util.Log
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