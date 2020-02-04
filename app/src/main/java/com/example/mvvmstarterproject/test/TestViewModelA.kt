package com.example.mvvmstarterproject.test

import com.example.mvvmstarterproject.base.BaseViewModel
import javax.inject.Inject

class TestViewModelA @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var testString: String
}
