package com.example.vineyardmanager.dataTypes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plot (
    @PrimaryKey(autoGenerate = true)
    val plotID: Long = 0,
    val vineyardID: Long?,
    val name: String?
)