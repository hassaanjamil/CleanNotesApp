package com.app.cleannotesapp.feature_note.data.data_source.remote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey
    @ColumnInfo("code")
    var code: String,
    @ColumnInfo("rate")
    var rate: Double,
    @ColumnInfo("name")
    val name: String,
)

data class CurrencyName(
    val code: String,
    val name: String,
)

data class CurrencyRate(
    val code: String,
    val rate: Double,
)
