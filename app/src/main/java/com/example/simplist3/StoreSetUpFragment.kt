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

class StoreSetUpFragment : Fragment() {
    private lateinit var binding: FragmentStoreSetUpBinding
    private lateinit var viewModel: StoreSetUpViewModel
    private lateinit var viewModelFactory: StoreSetUpViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("test", "in store set up fragment")
        binding = FragmentStoreSetUpBinding.inflate(inflater)

        //build the database if it doesn't already exist and get a reference to the StoreLocationDao
        val application = requireNotNull(this.activity).application
        val dao = ShoppingDatabase.getInstance(application).storeLocationDao

        //get the viewmodel
        viewModelFactory = StoreSetUpViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StoreSetUpViewModel::class.java)

        Log.i("test", "created binding and viewmodel")
        //set observers
        viewModel.locationsString.observe(viewLifecycleOwner, Observer {locations ->
            Log.i("test", "data observed as changed")
            binding.storeSetUpTvStoreLocationList.text = locations
            Log.i("test", "text: $locations")
            binding.storeSetUpEtLocationName.setText("")
            hideKeyboard()
        })
        Log.i("test", "set observer")
        //set onclick listeners
        binding.storeSetUpBtAdd.setOnClickListener { addLocation() }
        Log.i("test", "set listeners")
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addLocation() {
        Log.i("test", "in add location in fragment")
        viewModel.newStoreLocationName = binding.storeSetUpEtLocationName.text.toString()
        viewModel.addLocation()
        Log.i("test", "finished add location in fragment")
    }

    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.storeSetUpBtAdd.windowToken, 0)
    }
}