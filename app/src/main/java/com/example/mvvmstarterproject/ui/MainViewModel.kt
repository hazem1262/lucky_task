package com.example.mvvmstarterproject.ui

import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.utils.coroutines.ContextProviders
import javax.inject.Inject

class MainViewModel @Inject constructor(contextProvider: ContextProviders) : BaseViewModel(contextProvider)