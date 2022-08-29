package com.example.simplist3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "store_location_table")
data class StoreLocation (
    @PrimaryKey(autoGenerate = true)
    var storeLocationId: Long = 0L,

    @ColumnInfo(name = "store_location_first")
    var storeLocationFirst: Boolean = false,

    //This will tell us which storeLocationId comes next
    @ColumnInfo(name = "store_location_next")
    var storeLocationNext: Long = 0L,
)