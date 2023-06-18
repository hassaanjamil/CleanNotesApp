package com.app.cleannotesapp.feature_note.data.use_cases

import com.cleannotes.domain.model.Note
import com.cleannotes.domain.repository.NoteRepository
import com.cleannotes.domain.use_case.GetNote

class GetNoteImpl(
    private val repository: NoteRepository
) : GetNote {

    override suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}