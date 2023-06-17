package com.app.cleannotesapp.feature_note.data.use_cases

import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.feature_note.domain.use_case.GetNote

class GetNoteImpl(
    private val repository: NoteRepository
) : GetNote {

    override suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}