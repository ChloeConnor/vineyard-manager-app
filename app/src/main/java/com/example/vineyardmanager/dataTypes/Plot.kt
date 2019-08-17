package com.example.vineyardmanager.dataTypes

import androidx.room.Entity

@Entity
data class Plot (
    val vineyardId: Long,
    val name: String
)