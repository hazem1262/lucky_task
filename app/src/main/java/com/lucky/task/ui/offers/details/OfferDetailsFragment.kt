package com.lucky.task.ui.offers.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lucky.task.R
import com.lucky.task.base.BaseFragment
import com.lucky.task.databinding.OfferDetailsFragmentBinding
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import kotlinx.android.synthetic.main.offer_details_fragment.*

class OfferDetailsFragment : BaseFragment<OfferDetailsViewModel>() {

    private val args: OfferDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OfferDetailsFragmentBinding>(inflater, R.layout.offer_details_fragment, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            offer = args.offer
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getOfferDetails(args.offer.detailUrl?:"")
        handleBackButton()
    }

    private fun handleBackButton() {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun showLoading() {
        val doubleBounce: Sprite = ThreeBounce()
        descriptionLoading.setIndeterminateDrawable(doubleBounce)
        descriptionLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        descriptionLoading.visibility = View.GONE
    }
}