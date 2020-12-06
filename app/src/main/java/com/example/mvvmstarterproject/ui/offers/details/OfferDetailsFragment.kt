package com.example.mvvmstarterproject.ui.offers.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import kotlinx.android.synthetic.main.offer_details_fragment.*

class OfferDetailsFragment : BaseFragment<OfferDetailsViewModel>() {

    private val args: OfferDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offer_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        offerTitle.text = args.offer.title
    }

}