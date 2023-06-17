package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.util.NoteOrder
import com.app.cleannotesapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow

interface GetNotes {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>>
}