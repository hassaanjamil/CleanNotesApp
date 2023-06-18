package com.app.cleannotesapp.feature_note.presentation.notes

import com.cleannotes.domain.model.Note
import com.cleannotes.domain.util.NoteOrder
import com.cleannotes.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
