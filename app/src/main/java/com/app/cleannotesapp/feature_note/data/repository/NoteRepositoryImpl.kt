package com.app.cleannotesapp.feature_note.data.repository

import com.app.cleannotesapp.feature_note.data.data_source.local.CurrencyDao
import com.app.cleannotesapp.feature_note.data.data_source.model.CurrencyExchangeRateDto
import com.app.cleannotesapp.feature_note.data.data_source.remote.BaseApiResponse
import com.app.cleannotesapp.feature_note.data.data_source.remote.Currency
import com.app.cleannotesapp.feature_note.data.data_source.remote.CurrencyName
import com.app.cleannotesapp.feature_note.data.data_source.remote.CurrencyRate
import com.app.cleannotesapp.feature_note.data.data_source.remote.CurrencyService
import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao,
    private val currencyService: CurrencyService,
) : NoteRepository, BaseApiResponse() {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override fun observeCurrencies(): Flow<List<Currency>> {
        return currencyDao.getAllCurrenciesFlow().map { list ->
            var resultList = list.map { it.toModel() }
            if (list.isEmpty()) {
                val exchangeRatesResponse =
                    api.getExchangeRates().currencyRatesResponse.currenciesList
                val countriesNamesResponse =
                    api.getCountriesNames().currenciesMap

                countriesNamesResponse.forEach { (currencyCode, countryName) ->
                    exchangeRatesResponse.find { it.currencyCode == currencyCode }?.let {
                        it.countryName = countryName
                    }
                }

                currencyDao.save(exchangeRatesResponse)
                resultList = exchangeRatesResponse.map { it.toModel() }
                resultList
            } else
                resultList

        }
    }

    override suspend fun getCurrenciesData(): List<Currency> {
        coroutineScope {
            val result1Deferred = async { currencyService.getCurrencies() }
            val result2Deferred = async { currencyService.getCurrencyRates(Constants.APP_ID) }

            val result1 = result1Deferred.await()
            val result2 = result2Deferred.await()

            val listNames =
                result1.body()?.map { (code, name) -> CurrencyName(code = code, name = name) }!!
            val listRates = result2.body()?.rates?.map { (code, rate) ->
                CurrencyRate(
                    code = code,
                    rate = rate
                )
            }!!

            return@coroutineScope mergeLists(listRates, listNames)
        }
        return emptyList()
    }

    fun mergeLists(
        currencyRates: List<CurrencyRate>,
        currencyNames: List<CurrencyName>,
    ): List<Currency> {
        val mergedList = mutableListOf<Currency>()

        for (currencyRate in currencyRates) {
            val matchingName = currencyNames.find { it.code == currencyRate.code }
            val currency = matchingName?.let {
                Currency(
                    code = currencyRate.code,
                    rate = currencyRate.rate,
                    name = it.name
                )
            }
            mergedList.add(currency!!)
        }

        return mergedList
    }

    override suspend fun getCurrencies(): Response<Map<String, String>> {
        return currencyService.getCurrencies()
    }

    override suspend fun getCurrencyRates(): Response<CurrencyExchangeRateDto> {
        return currencyService.getCurrencyRates(Constants.APP_ID)
    }
}
