package com.app.cleannotesapp.feature_note.data.use_cases

import com.app.cleannotesapp.feature_note.domain.model.InvalidNoteException
import com.app.cleannotesapp.feature_note.domain.model.Note
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.feature_note.domain.use_case.AddNote

class AddNoteImpl(
    private val repository: NoteRepository
) : AddNote {

    @Throws(InvalidNoteException::class)
    override suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}