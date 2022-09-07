package com.example.simplist3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StoreSetUpViewModel (val dao: StoreLocationDao) : ViewModel() {
    var newStoreLocationName = ""

    private val locations = dao.getAll()
    //Transforms the tasks into a LiveData<String>, which is assigned to tasksSttring.
    //Since this is a live data object, it will automatically get updated if the data changes
    val locationsString = Transformations.map(locations) { locations ->
        formatLocations(locations)
    }

    fun addLocation() {
        Log.i("test", "in add location in view model")
        //launch the coroutine in the same scope as the view model
        viewModelScope.launch{
            val location = StoreLocation()
            location.storeLocationName = newStoreLocationName
            dao.insert(location)
        }
        Log.i("test", "finished add location in view model")
    }

    //formats a list of tasks as a String.
    fun formatLocations(locations: List<StoreLocation>): String {
        Log.i("test", "in format locations")
        return locations.fold("") {
            str, item -> str + '\n' + formatLocation(item)
        }
    }
    //formats each task as a string
    fun formatLocation(location: StoreLocation): String {
        Log.i("test", "in format location")
        var str = "ID: ${location.storeLocationId}"
        str += '\n' + "Name: ${location.storeLocationName}"
        str += '\n' + "Rank: ${location.storeLocationRank}" + '\n'
        Log.i("test", "returned string is $str")
        return str
    }
}