package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.feature_note.domain.model.InvalidNoteException
import com.app.cleannotesapp.feature_note.domain.model.Note

interface AddNote {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note)
}