package com.app.cleannotesapp.feature_note.data.data_source.remote

import com.app.cleannotesapp.feature_note.data.data_source.model.CurrencyExchangeRateDto
import com.app.cleannotesapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET(Constants.CURRENCIES)
    suspend fun getCurrencies(): Response<Map<String, String>>

    @GET(Constants.CURRENCY_RATES)
    suspend fun getCurrencyRates(@Query("app_id") appId: String): Response<CurrencyExchangeRateDto>
}
