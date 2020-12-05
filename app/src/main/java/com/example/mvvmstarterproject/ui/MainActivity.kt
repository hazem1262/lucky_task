package com.example.mvvmstarterproject.ui

import android.os.Bundle
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
