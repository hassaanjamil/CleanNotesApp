package com.app.cleannotesapp.feature_note.data.use_cases

import com.cleannotes.domain.model.Note
import com.cleannotes.domain.repository.NoteRepository
import com.cleannotes.domain.use_case.DeleteNote

class DeleteNoteImpl(
    private val repository: NoteRepository
) : DeleteNote {
    override suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}