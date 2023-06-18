package com.cleannotes.domain.use_case

import com.cleannotes.domain.model.Note

interface GetNote {
    suspend operator fun invoke(id: Int): Note?
}