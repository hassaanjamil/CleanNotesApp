package com.app.cleannotesapp.feature_note.data.data_source.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val currencyService: CurrencyService) {
    suspend fun getCurrencies() = currencyService.getCurrencies()

}
