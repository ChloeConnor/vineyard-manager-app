package com.example.vineyardmanager.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.vineyardmanager.dataTypes.Plot
import com.example.vineyardmanager.dataTypes.Vineyard
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration




@Database(entities = [Vineyard::class, Plot::class], version = 3)
abstract class VineyardManagerDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        private var db: VineyardManagerDatabase? = null

        fun getAppDatabase(context: Context): VineyardManagerDatabase {
            var d = db
            if (d == null) {
                d =  Room.databaseBuilder(context.applicationContext, VineyardManagerDatabase::class.java, "vineyard-db")
                    //FIXME: this will delete users data if db schema changes
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return d
        }

        //FIXME: create proper migration when db is updated
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        fun destroyInstance() {
            db = null
        }
    }
}