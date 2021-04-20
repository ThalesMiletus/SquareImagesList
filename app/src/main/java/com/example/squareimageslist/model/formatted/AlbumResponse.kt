package com.example.squareimageslist.model.formatted

import android.os.Parcelable
import com.example.squareimageslist.constant.RetrofitStatus
import com.example.squareimageslist.model.response.Album
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumResponse(
    val status: RetrofitStatus = RetrofitStatus.UNKNOWN,
    val albums: List<Album>? = null
) : Parcelable