package com.example.mvvmstarterproject.test

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(),HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var testDagger:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.printTestString()
        Log.d("test", viewModel.toString())
        test.text = testDagger
        initFragment()
    }

    private fun initFragment() {
        val testFragmentA = TestFragmentA.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, testFragmentA).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
