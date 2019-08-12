package com.example.vineyardmanager.dataTypes

data class Vineyard(
    val name: String,
    val plots: Set<Plot> = emptySet()
)