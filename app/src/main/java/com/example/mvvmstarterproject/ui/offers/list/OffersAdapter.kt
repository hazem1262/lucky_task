package com.example.mvvmstarterproject.ui.offers.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.data.remote.offers.OffersResponse.Section.Offer
import com.example.mvvmstarterproject.databinding.OfferRowItemBinding

class OffersAdapter: ListAdapter<Offer, RecyclerView.ViewHolder>(diffCallBack)  {
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Offer>() {
            override fun areItemsTheSame(
                oldItem: Offer,
                newItem: Offer
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                oldItem: Offer,
                newItem: Offer
            ): Boolean = oldItem.detailUrl == newItem.detailUrl

        }
        @JvmStatic
        @BindingAdapter("offers")
        fun RecyclerView.bindItems(items: MutableLiveData<List<Offer>>?) {
            val adapter = adapter as OffersAdapter
            items?.observeForever {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OfferViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.offer_row_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OfferViewHolder).bind(currentList[position])
    }

    inner class OfferViewHolder(private var binding: OfferRowItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:Offer){
            binding.apply {
                offer = item
                executePendingBindings()
            }
        }
    }
}