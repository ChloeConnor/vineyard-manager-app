package com.example.vineyardmanager.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.vineyardmanager.dataTypes.Vineyard


@Database(entities = [Vineyard::class], version = 1)
abstract class VineyardManagerDatabase : RoomDatabase() {

    abstract fun dao(): vineyardDao

    companion object {
        private var db: VineyardManagerDatabase? = null

        fun getAppDatabase(context: Context): VineyardManagerDatabase {
            var d = db
            if (d == null) {
                d =  Room.databaseBuilder(context.applicationContext, VineyardManagerDatabase::class.java, "vineyard-db")
                    // don't do this on a real app!
                    .allowMainThreadQueries()
                    .build()
            }

            return d
        }

        fun destroyInstance() {
            db = null
        }
    }
}