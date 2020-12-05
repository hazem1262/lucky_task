package com.example.mvvmstarterproject.ui

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.data.remote.offers.User
import javax.inject.Inject

class TestViewModelA @Inject constructor(private val testRepositoryA:TestRepositoryA) : BaseViewModel() {
    @Inject
    lateinit var testString: String
    val userLiveData:MutableLiveData<List<User>> = MutableLiveData()
    fun getUserDetails(){
        wrapBlockingOperation {
            handleResult(testRepositoryA.getListOfUsers()){
                userLiveData.postValue(it.data)
            }
        }
    }
}
