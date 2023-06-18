package com.app.cleannotesapp.feature_note.presentation.notes

import com.cleannotes.domain.model.Note
import com.cleannotes.domain.util.NoteOrder


sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
