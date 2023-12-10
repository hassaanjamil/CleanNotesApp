package com.app.cleannotesapp.feature_note.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.cleannotesapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract val dao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}
