package com.example.vineyardmanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.vineyardmanager.dataTypes.Vineyard
import androidx.room.Insert


@Dao
interface vineyardDao {

    @Query("SELECT * FROM Vineyard")
    fun loadVineyards(): List<Vineyard>

    @Insert
    fun insertVineyard(vararg vineyard: Vineyard)

    @Query("DELETE FROM Vineyard WHERE name is null")
    fun deleteVineyardNullNames()

}
