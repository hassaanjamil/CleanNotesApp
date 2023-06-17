package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.domain.use_case.DeleteNote

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
