package com.example.simplist3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "house_location_table")
data class HouseLocation (
    @PrimaryKey(autoGenerate = true)
    var houseLocationId: Long = 0L,

    @ColumnInfo(name = "house_location_name")
    var houseLocationName: String = "",

    @ColumnInfo(name = "house_location_rank")
    var houseLocationRank: Int = 0,
)