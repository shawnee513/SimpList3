package com.example.simplist3

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    //Gets all of the items at the specified store location
    @Query("SELECT * FROM item_table WHERE item_store_location_id = :itemStoreLocationId")
    fun getItemsInStoreLocation(itemStoreLocationId: Long): LiveData<List<Item>>

    //Gets all of the items at the specified house location
    @Query("SELECT * FROM item_table WHERE item_house_location_id = :itemHouseLocationId")
    fun getItemsInHouseLocation(itemHouseLocationId: Long): LiveData<List<Item>>
}