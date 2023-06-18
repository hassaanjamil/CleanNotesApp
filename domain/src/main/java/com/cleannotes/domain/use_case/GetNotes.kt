package com.cleannotes.domain.use_case

import com.cleannotes.domain.model.Note
import com.cleannotes.domain.util.NoteOrder
import com.cleannotes.domain.util.OrderType
import kotlinx.coroutines.flow.Flow

interface GetNotes {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>>
}