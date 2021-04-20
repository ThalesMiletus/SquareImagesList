package com.example.squareimageslist.dao.remote

import com.example.squareimageslist.model.response.Album
import retrofit2.http.GET

interface RemoteAlbumDao {
    @GET("albums/1/photos")
    suspend fun getAlbums(): List<Album>
}