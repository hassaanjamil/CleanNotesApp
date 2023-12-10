package com.app.cleannotesapp.feature_note.domain.repository

import com.app.cleannotesapp.feature_note.data.data_source.model.CurrencyExchangeRateDto
import com.app.cleannotesapp.feature_note.data.data_source.remote.Currency
import com.app.cleannotesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getCurrenciesData(): Response<Currency>

    suspend fun getCurrencies(): Response<Map<String, String>>

    suspend fun getCurrencyRates(): Response<CurrencyExchangeRateDto>
}
