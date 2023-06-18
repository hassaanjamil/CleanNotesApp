package com.app.cleannotesapp.di

import android.app.Application
import androidx.room.Room
import com.cleannotes.data.data_source.NoteDatabase
import com.cleannotes.data.repository.NoteRepositoryImpl
import com.cleannotes.data.use_cases.AddNoteImpl
import com.cleannotes.data.use_cases.DeleteNoteImpl
import com.cleannotes.data.use_cases.GetNoteImpl
import com.cleannotes.data.use_cases.GetNotesImpl
import com.cleannotes.domain.repository.NoteRepository
import com.cleannotes.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesImpl(repository),
            deleteNote = DeleteNoteImpl(repository),
            addNote = AddNoteImpl(repository),
            getNote = GetNoteImpl(repository)
        )
    }
}