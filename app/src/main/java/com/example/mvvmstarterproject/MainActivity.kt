package com.example.mvvmstarterproject

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmstarterproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var testDagger:String
    
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.printTestString()
        Log.d("test", viewModel.toString())
        test.text = testDagger
    }
}
