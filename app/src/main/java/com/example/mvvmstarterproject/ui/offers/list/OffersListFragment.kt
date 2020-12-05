package com.example.mvvmstarterproject.ui.offers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment

class OffersListFragment : BaseFragment<OffersListViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offers_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getOffersList()
        viewModel.offersLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

}