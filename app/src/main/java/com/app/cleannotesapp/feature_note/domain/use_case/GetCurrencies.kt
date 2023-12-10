package com.app.cleannotesapp.feature_note.domain.use_case

import com.app.cleannotesapp.feature_note.data.data_source.remote.BaseApiResponse
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrencies @Inject constructor(
    private val noteRepository: NoteRepository,
) : BaseApiResponse() {
    operator fun invoke(): Flow<NetworkResult<Map<String, String>>> {
        return flow {
            emit(safeApiCall { noteRepository.getCurrencies() })
        }.flowOn(Dispatchers.IO)
    }
}
