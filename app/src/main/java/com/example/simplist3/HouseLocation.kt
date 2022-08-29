package com.example.simplist3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "house_location_table")
data class HouseLocation (
    @PrimaryKey(autoGenerate = true)
    var houseLocationId: Long = 0L,

    @ColumnInfo(name = "house_location_first")
    var houseLocationFirst: Boolean = false,

    //This will tell us which houseLocationId comes next
    @ColumnInfo(name = "house_location_next")
    var houseLocationNext: Long = 0L,
)