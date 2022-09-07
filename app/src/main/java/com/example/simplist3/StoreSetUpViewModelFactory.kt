package com.example.simplist3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StoreSetUpViewModelFactory (private val dao: StoreLocationDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StoreSetUpViewModel::class.java)) {
            return StoreSetUpViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}