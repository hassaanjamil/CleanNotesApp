package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.feature_note.domain.model.Note

interface DeleteNote {
    suspend operator fun invoke(note: Note)
}