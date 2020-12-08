package com.lucky.task.ui

import android.os.Bundle
import com.lucky.task.R
import com.lucky.task.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
