package com.example.mvvmstarterproject.ui.offers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import com.example.mvvmstarterproject.data.remote.offers.OffersResponse.Section.Offer
import com.example.mvvmstarterproject.databinding.OffersListFragmentBinding
import com.example.mvvmstarterproject.ui.offers.details.OfferDetailsFragmentArgs

class OffersListFragment : BaseFragment<OffersListViewModel>() {

    private val offersAdapter = OffersAdapter(::navigateToOfferDetails)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getOffersList()
    }

    private fun navigateToOfferDetails(offer:Offer){
        val args = OfferDetailsFragmentArgs(offer)
        findNavController().navigate(R.id.offerDetailsFragment, args.toBundle())
    }
}