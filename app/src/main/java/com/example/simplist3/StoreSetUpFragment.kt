package com.example.simplist3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplist3.databinding.FragmentStoreSetUpBinding
import androidx.lifecycle.Observer
import com.example.simplist3.databinding.StoreLocationItemBinding

class StoreSetUpFragment : Fragment() {
    private lateinit var binding: FragmentStoreSetUpBinding
    private lateinit var viewModel: StoreSetUpViewModel
    private lateinit var viewModelFactory: StoreSetUpViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreSetUpBinding.inflate(inflater)
        Log.i("test", "logcat is working")

        //build the database if it doesn't already exist and get a reference to the StoreLocationDao
        val application = requireNotNull(this.activity).application
        val dao = ShoppingDatabase.getInstance(application).storeLocationDao

        //get the viewmodel
        viewModelFactory = StoreSetUpViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StoreSetUpViewModel::class.java)

        //set onclick listeners
        binding.storeSetUpBtAdd.setOnClickListener { addLocation() }

        //connect the adapter to the recycler view
        val adapter = StoreLocationItemAdapter (
            { storeLocationId, rank, name -> viewModel.lowerRank(storeLocationId, rank, name)},
            {storeLocationId, rank, name -> viewModel.higherRank(storeLocationId, rank, name) },
            {storeLocationId -> viewModel.onStoreLocationClicked(storeLocationId)})

        binding.storeSetUpRvStoreLocationList.adapter = adapter

        //set observer and give to adapter
        viewModel.locations.observe(viewLifecycleOwner, Observer {
            it?.let {
                //the way of doing it before using DiffUtil
                /*adapter.data = it*/
                //now the way to do it using DiffUtil - passing new data to the adapter's backing list
                adapter.submitList(it)
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addLocation() {
        viewModel.newStoreLocationName = binding.storeSetUpEtLocationName.text.toString()
        viewModel.addLocation()
    }

    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.storeSetUpBtAdd.windowToken, 0)
    }
}