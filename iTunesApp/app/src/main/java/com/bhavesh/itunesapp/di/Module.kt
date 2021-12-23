package com.bhavesh.itunesapp.di

import android.content.Context
import androidx.room.Room
import com.bhavesh.itunesapp.remote.ApiService
import com.bhavesh.itunesapp.room.ITunesDAO
import com.bhavesh.itunesapp.room.ITunesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {
    const val BASE_URL = "https://itunes.apple.com/"

    @Provides
    fun getApiService(): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun iTunesRoomDatabase(@ApplicationContext context: Context): ITunesRoomDatabase {
        val builder = Room.databaseBuilder(
            context, ITunesRoomDatabase::class.java, "itunes.db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDataToDao(db: ITunesRoomDatabase): ITunesDAO {
        return db.getDataFromDB()
    }
}