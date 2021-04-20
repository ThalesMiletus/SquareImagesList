package com.example.squareimageslist.repasitory

import com.example.squareimageslist.dao.remote.RemoteAlbumDao
import com.example.squareimageslist.model.response.Album
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class AlbumRepository @Inject constructor(private val albumDao: RemoteAlbumDao) {
    suspend fun getAlbums(): List<Album> = albumDao.getAlbums()
}