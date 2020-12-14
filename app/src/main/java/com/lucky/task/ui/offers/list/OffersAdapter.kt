package com.lucky.task.ui.offers.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucky.task.R
import com.lucky.task.data.remote.offers.OffersResponse.Section.Offer
import com.lucky.task.databinding.OfferRowItemBinding
import com.lucky.task.databinding.OffersHeaderBinding

class OffersAdapter(val selectOffer:(Offer)->Unit): ListAdapter<Offer, RecyclerView.ViewHolder>(diffCallBack)  {
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
        return when(viewType){
            ITEM_VIEW_TYPE_HEADER -> OffersHeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.offers_header, parent, false
                )
            )
            ITEM_VIEW_TYPE_ITEM -> OfferViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.offer_row_item, parent, false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ITEM_VIEW_TYPE_HEADER else ITEM_VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OffersHeaderViewHolder -> holder.bind()
            is OfferViewHolder -> holder.bind(currentList[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 0) currentList.size + 1 else 0
    }

    inner class OfferViewHolder(private var binding: OfferRowItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:Offer){
            binding.apply {
                root.setOnClickListener {
                    selectOffer(item)
                }
                offer = item
                executePendingBindings()
            }
        }
    }

    inner class OffersHeaderViewHolder(private var binding: OffersHeaderBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                offersCount = currentList.size
                executePendingBindings()
            }
        }
    }
}

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1
