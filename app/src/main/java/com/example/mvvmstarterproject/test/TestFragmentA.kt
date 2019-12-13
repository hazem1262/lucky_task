package com.example.mvvmstarterproject.test

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment


class TestFragmentA : BaseFragment() {

    /*@Inject
    lateinit var testDagger:String*/

    companion object {
        fun newInstance() = TestFragmentA()
    }

    private lateinit var viewModel: TestViewModelA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestViewModelA::class.java)
//        test.text = testDagger
    }

}
