package com.example.mvvmstarterproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmstarterproject.base.BaseActivity
import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
