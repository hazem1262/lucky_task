package com.example.mvvmstarterproject.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import kotlinx.android.synthetic.main.test_fragment_a.*


class TestFragmentA : BaseFragment<TestViewModelA>() {

    companion object {
        fun newInstance() = TestFragmentA()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        test.text = viewModel.testString
        viewModel.getUserDetails()
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

}
