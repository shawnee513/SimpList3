package com.example.simplist3

import androidx.recyclerview.widget.DiffUtil

class StoreLocationDiffItemCallback : DiffUtil.ItemCallback<StoreLocation>() {
    override fun areItemsTheSame(oldItem: StoreLocation, newItem: StoreLocation)
            = (oldItem.storeLocationId == newItem.storeLocationId)

    override fun areContentsTheSame(oldItem: StoreLocation, newItem: StoreLocation)
            = (oldItem == newItem)
}