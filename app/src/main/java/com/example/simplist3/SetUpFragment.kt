package com.example.simplist3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.simplist3.databinding.FragmentSetUpBinding
import com.example.simplist3.databinding.FragmentStoreSetUpBinding


class SetUpFragment : Fragment() {
    private lateinit var binding: FragmentSetUpBinding
    private lateinit var viewModel: SetUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetUpBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SetUpViewModel::class.java)

        //set on click listeners
        binding.setUpBtStoreSetUp.setOnClickListener { viewModel.navigateToStore() }
        binding.setUpBtHouseSetUp.setOnClickListener { viewModel.navigateToHouse() }
        binding.setUpBtItemSetUp.setOnClickListener { viewModel.navigateToItem() }

        //set observers
        viewModel.navigateToStore.observe (viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                findNavController().navigate(SetUpFragmentDirections.actionSetUpFragmentToStoreSetUpFragment())
                viewModel.navigatedToStore()
            }
        })

        viewModel.navigateToHouse.observe (viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                findNavController().navigate(SetUpFragmentDirections.actionSetUpFragmentToHouseSetUpFragment())
                viewModel.navigatedToHouse()
            }
        })

        viewModel.navigateToItem.observe (viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                findNavController().navigate(SetUpFragmentDirections.actionSetUpFragmentToItemSetUpFragment())
                viewModel.navigatedToItem()
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}