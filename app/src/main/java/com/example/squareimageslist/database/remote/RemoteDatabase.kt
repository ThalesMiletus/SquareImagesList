package com.example.squareimageslist.database.remote

import com.example.squareimageslist.dao.remote.RemoteAlbumDao
import com.example.squareimageslist.utils.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatabase {

    @Provides
    @Singleton
    fun provideAlbumDAO(): RemoteAlbumDao = Retrofit.getClient().create(RemoteAlbumDao::class.java)
}