package com.example.simplist3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetUpViewModel: ViewModel(){
    private val _navigateToStore = MutableLiveData<Boolean>(false)
    val navigateToStore: LiveData<Boolean> get() = _navigateToStore

    private val _navigateToHouse = MutableLiveData<Boolean>(false)
    val navigateToHouse: LiveData<Boolean> get() = _navigateToHouse

    private val _navigateToItem = MutableLiveData<Boolean>(false)
    val navigateToItem: LiveData<Boolean> get() = _navigateToItem

    fun navigateToStore(){
        _navigateToStore.value = true
    }

    fun navigateToHouse() {
        _navigateToHouse.value = true
    }

    fun navigateToItem() {
        _navigateToItem.value = true
    }

    fun navigatedToStore() {
        _navigateToStore.value = false
    }

    fun navigatedToHouse() {
        _navigateToHouse.value = false
    }

    fun navigatedToItem() {
        _navigateToItem.value = false
    }
}