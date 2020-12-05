package com.example.mvvmstarterproject.ui

import android.os.Bundle
import android.util.Log
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var testDagger:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.printTestString()
        Log.d("test", viewModel.toString())
        test.text = testDagger
        initFragment()
    }

    private fun initFragment() {
        val testFragmentA = TestFragmentA.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, testFragmentA).commit()
    }

}
