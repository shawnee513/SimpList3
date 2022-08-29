package com.example.simplist3

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HouseLocationDao {
    @Insert
    suspend fun insert(houseLocation: HouseLocation)

    @Update
    suspend fun update(houseLocation: HouseLocation)

    @Delete
    suspend fun delete(houseLocation: HouseLocation)

    @Query("SELECT * FROM house_location_table WHERE houseLocationId = :houseLocationNext")
    fun get(houseLocationNext: Long): HouseLocation
}