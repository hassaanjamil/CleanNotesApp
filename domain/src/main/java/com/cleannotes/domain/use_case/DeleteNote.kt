package com.cleannotes.domain.use_case

import com.cleannotes.domain.model.Note

interface DeleteNote {
    suspend operator fun invoke(note: Note)
}