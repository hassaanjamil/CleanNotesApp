package com.cleannotes.domain.use_case

import com.cleannotes.domain.model.InvalidNoteException
import com.cleannotes.domain.model.Note

interface AddNote {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note)
}