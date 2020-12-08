package com.lucky.task.ui.offers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lucky.task.R
import com.lucky.task.base.BaseFragment
import com.lucky.task.data.remote.offers.OffersResponse.Section.Offer
import com.lucky.task.databinding.OffersListFragmentBinding
import com.lucky.task.ui.offers.details.OfferDetailsFragmentArgs

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