package com.example.simplist3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplist3.databinding.StoreLocationItemBinding

//the way of doing it using DiffUtil
//to make items clickable, taskItemAdapter will accept a clickListener from the fragment
//and will pass it to onBindViewHolder
//this makes the fragment responsible for what happens when the item is clicked
class StoreLocationItemAdapter(val lowerClicked: (storeLocationId: Long, rank: Int, name: String) -> Unit,
                               val higherClicked: (storeLocationId: Long, rank: Int, name: String) -> Unit,
                            val clickListener: (storeLocationId: Long) -> Unit) : ListAdapter<StoreLocation, StoreLocationItemAdapter.StoreLocationItemViewHolder>(StoreLocationDiffItemCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : StoreLocationItemViewHolder = StoreLocationItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: StoreLocationItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, lowerClicked, higherClicked, clickListener)
    }

    class StoreLocationItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        //set binding so we can reference views
        private val binding = StoreLocationItemBinding.bind(rootView)


        //creating this inside a companion object means that it can be called without first creating a TaskItemViewHolder object
        companion object {
            fun inflateFrom(parent: ViewGroup): StoreLocationItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.store_location_item, parent, false) as CardView
                //The method inflates the item's layout, and uses it to create a TaskItemViewHolder
                return StoreLocationItemViewHolder(view)
            }
        }

        fun bind(item: StoreLocation, lowerClicked: (storeLocationId: Long, rank: Int, name: String) -> Unit, higherClicked: (storeLocationId: Long, rank: Int, name: String) -> Unit, clickListener: (storeLocationId: Long) -> Unit) {
            //add the task name to the layout's root view (a text view)
            binding.storeLocationItemTvId.text = item.storeLocationId.toString()
            binding.storeLocationItemTvName.text = item.storeLocationName
            binding.storeLocationItemTvRank.text = item.storeLocationRank.toString()

            //set listeners for buttons
            binding.storeLocationItemBtLowerRank.setOnClickListener {
                Log.i("test", "calling lower rank")
                lowerClicked(item.storeLocationId, item.storeLocationRank, item.storeLocationName)
            }
            binding.storeLocationItemBtHigherRank.setOnClickListener {
                Log.i("test", "calling higher rank")
               higherClicked(item.storeLocationId, item.storeLocationRank, item.storeLocationName)
            }

            //make the item resond to clicks
            binding.root.setOnClickListener { clickListener(item.storeLocationId) }
        }
    }
}