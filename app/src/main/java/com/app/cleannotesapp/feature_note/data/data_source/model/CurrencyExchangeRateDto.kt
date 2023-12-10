package com.app.cleannotesapp.feature_note.data.data_source.model

data class CurrencyExchangeRateDto(
    val disclaimer: String,
    val license: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>,
)
