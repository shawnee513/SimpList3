package com.example.simplist3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simplist3.databinding.FragmentStoreSetUpBinding

class StoreSetUpFragment : Fragment() {
    private lateinit var binding: FragmentStoreSetUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreSetUpBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }
}