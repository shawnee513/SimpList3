package com.example.simplist3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "item_name")
    var itemName: String = "",

    @ColumnInfo(name = "item_qty")
    var itemQty: Int = 0,

    @ColumnInfo(name = "item_store_location_id")
    var itemStoreLocationId: Long = 0L,

    @ColumnInfo(name = "item_house_location_id")
    var itemHouseLocationId: Long = 0L,
)