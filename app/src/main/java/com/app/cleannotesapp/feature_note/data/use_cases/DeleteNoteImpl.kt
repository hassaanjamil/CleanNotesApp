package com.app.cleannotesapp.feature_note.data.use_cases

import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.feature_note.domain.use_case.DeleteNote

class DeleteNoteImpl(
    private val repository: NoteRepository
) : DeleteNote {
    override suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}