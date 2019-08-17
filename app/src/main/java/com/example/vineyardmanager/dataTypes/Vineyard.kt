package com.example.vineyardmanager.dataTypes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vineyard(
    @PrimaryKey(autoGenerate = true)
    val vineyardID: Long = 0,
    val name: String?,
    val client: String?, //FIXME: shouldn't be nullable
    val dateAdded: Long = System.currentTimeMillis(),
    val countBuds: Boolean = true,
    val countShoots: Boolean = true,
    val countFlowers: Boolean = true,
    val countGrapes: Boolean = true,
    val weight: Boolean = true

)
