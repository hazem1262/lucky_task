package com.example.mvvmstarterproject.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

class BaseFragment : Fragment(), HasAndroidInjector {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}