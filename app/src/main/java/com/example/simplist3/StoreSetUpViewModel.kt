package com.example.simplist3

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class StoreSetUpViewModel (val dao: StoreLocationDao) : ViewModel() {
    var newStoreLocationName = ""

    val locations = dao.getAll()

    /*//Transforms the tasks into a LiveData<String>, which is assigned to tasksSttring.
    //Since this is a live data object, it will automatically get updated if the data changes
    val locationsString = Transformations.map(locations) { locations ->
        formatLocations(locations)
    }*/

    private val _navigateToStoreLocation = MutableLiveData<Long?>()
    val navigateToStoreLocation: LiveData<Long?> get() = _navigateToStoreLocation

    private val _navigateToSetUp = MutableLiveData<Boolean>()
    val navigateToSetUp: LiveData<Boolean> get() = _navigateToSetUp

    fun addLocation() {
        //launch the coroutine in the same scope as the view model
        viewModelScope.launch{
            val location = StoreLocation()
            location.storeLocationName = newStoreLocationName
            dao.insert(location)
        }
    }

    fun onStoreLocationClicked(storeLocationId: Long){
        _navigateToStoreLocation.value = storeLocationId
    }

    fun onStoreLocationNavigated(){
        _navigateToStoreLocation.value = null
    }

    fun lowerRank(storeLocationId: Long, rank: Int, name: String){
        var newRank = rank - 1
        val location = StoreLocation(storeLocationId, name, newRank)
        viewModelScope.launch{
            dao.update(location)
        }
    }

    fun higherRank(storeLocationId: Long, rank: Int, name: String){
        Log.i("test", "called higher rank")
        var newRank = rank + 1
        val location = StoreLocation(storeLocationId, name, newRank)
        viewModelScope.launch{
            dao.update(location)
        }
    }

    fun navigateToSetUp() {
        _navigateToSetUp.value = true
    }

    fun navigatedToSetUp() {
        _navigateToSetUp.value = false
    }

   /* //formats a list of tasks as a String.
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
    }*/
}