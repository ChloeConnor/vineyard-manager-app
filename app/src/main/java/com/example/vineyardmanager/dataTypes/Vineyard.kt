package com.example.vineyardmanager.dataTypes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Vineyard(
    @PrimaryKey(autoGenerate = true)
    val vineyardID: Long = 0,
    val name: String?,
    val client: String? = "",
    val dateAdded: Long = System.currentTimeMillis(),
    val countBuds: Boolean = true
)
