package com.example.mvvmstarterproject.ui.offers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import com.example.mvvmstarterproject.databinding.OffersListFragmentBinding

class OffersListFragment : BaseFragment<OffersListViewModel>() {

    private val offersAdapter = OffersAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OffersListFragmentBinding>(inflater, R.layout.offers_list_fragment, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            offersRecyclerView.adapter = offersAdapter
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getOffersList()
    }

}