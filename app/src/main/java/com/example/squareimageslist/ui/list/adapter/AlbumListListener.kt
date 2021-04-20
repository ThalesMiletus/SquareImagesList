package com.example.squareimageslist.ui.list.adapter

import com.example.squareimageslist.model.response.Album

interface AlbumListListener {
    fun onItemClick(album: Album)
}