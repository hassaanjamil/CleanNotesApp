package com.app.cleannotesapp.di

import android.app.Application
import androidx.room.Room
import com.app.cleannotesapp.feature_note.data.data_source.local.CurrencyDatabase
import com.app.cleannotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.app.cleannotesapp.feature_note.domain.repository.NoteRepository
import com.app.cleannotesapp.feature_note.domain.use_case.AddNote
import com.app.cleannotesapp.domain.use_case.DeleteNote
import com.app.cleannotesapp.feature_note.data.data_source.remote.CurrencyService
import com.app.cleannotesapp.feature_note.data.data_source.remote.RemoteDataSource
import com.app.cleannotesapp.feature_note.domain.use_case.*
import com.app.cleannotesapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            app,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        db: CurrencyDatabase,
        currencyService: CurrencyService,
    ): NoteRepository {
        return NoteRepositoryImpl(db.dao, currencyService)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(
        repository: NoteRepository,
        remoteDataSource: RemoteDataSource,
    ): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            getCurrencies = GetCurrencies(repository)
        )
    }

    // DOGS PROVIDES

    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(createLoggingInterceptor())
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(dogService: CurrencyService): RemoteDataSource =
        RemoteDataSource(dogService)

    @Singleton
    @Provides
    fun ProvidesNotesRepositoryImpl(
        database: CurrencyDatabase,
        currencyService: CurrencyService,
    ): NoteRepositoryImpl =
        NoteRepositoryImpl(database.dao, currencyService)
}
