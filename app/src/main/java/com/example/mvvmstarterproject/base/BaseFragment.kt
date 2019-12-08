package com.example.mvvmstarterproject.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

open class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}