package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.feature_note.domain.model.Note

interface GetNote {
    suspend operator fun invoke(id: Int): Note?
}