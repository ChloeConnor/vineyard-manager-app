package com.example.vineyardmanager.database

import androidx.room.*
import androidx.room.Dao
import com.example.vineyardmanager.dataTypes.Vineyard
import com.example.vineyardmanager.dataTypes.Plot


@Dao
interface Dao {

    @Query("SELECT * FROM Vineyard")
    fun loadVineyards(): List<Vineyard>

    @Insert
    fun insertVineyard(vararg vineyard: Vineyard)

    @Query("DELETE FROM Vineyard WHERE name is null")
    fun deleteVineyardNullNames()

    @Query("SELECT * FROM Plot")
    fun loadPlots(): List<Plot>

    @Insert
    fun insertPlot(vararg plot: Plot)

    @Query("DELETE FROM Plot WHERE name is null")
    fun deletePlotNullNames()
}
