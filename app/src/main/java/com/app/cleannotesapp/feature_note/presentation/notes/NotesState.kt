package com.app.cleannotesapp.feature_note.presentation.notes

import com.app.cleannotesapp.feature_note.data.data_source.remote.Currency
import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.util.NoteOrder
import com.app.cleannotesapp.feature_note.domain.util.OrderType

data class NotesState(
    val currencies: List<Currency> = emptyList(),
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)
