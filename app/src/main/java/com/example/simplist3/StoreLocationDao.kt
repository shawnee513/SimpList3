package com.example.simplist3

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StoreLocationDao {
    @Insert
    suspend fun insert(storeLocation: StoreLocation)

    @Update
    suspend fun update(storeLocation: StoreLocation)

    @Delete
    suspend fun delete(storeLocation: StoreLocation)

    //Gets the next store location
    @Query("SELECT * FROM store_location_table WHERE storeLocationId = :storeLocationNext")
    fun get(storeLocationNext: Long): StoreLocation
}