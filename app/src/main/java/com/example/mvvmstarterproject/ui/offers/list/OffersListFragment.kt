package com.example.mvvmstarterproject.ui.offers.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmstarterproject.R

class OffersListFragment : Fragment() {

    companion object {
        fun newInstance() = OffersListFragment()
    }

    private lateinit var viewModel: OffersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offers_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OffersListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}