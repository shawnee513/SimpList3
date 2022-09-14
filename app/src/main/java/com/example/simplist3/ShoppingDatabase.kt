package com.example.simplist3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class, StoreLocation::class, HouseLocation::class], version = 12, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase(){
    //Specify Dao interfaces
    abstract val itemDao: ItemDao
    abstract val storeLocationDao: StoreLocationDao
    abstract val houseLocationDao: HouseLocationDao

    //create the database and return an instance of it
    companion object{
        @Volatile
        private var INSTANCE: ShoppingDatabase? = null

        fun getInstance(context: Context) : ShoppingDatabase {
            synchronized(this) {
                var instance = INSTANCE
                //if the database doesn't already exist, the getInstance() method goes and builds it
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShoppingDatabase::class.java,
                        "shopping_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}