package com.example.squareimageslist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squareimageslist.constant.RetrofitStatus
import com.example.squareimageslist.model.formatted.AlbumResponse
import com.example.squareimageslist.repasitory.AlbumRepository
import com.example.squareimageslist.utils.Retrofit.printRetrofitError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _albums = MutableLiveData<AlbumResponse>()
    val albums: LiveData<AlbumResponse> = _albums

    init {
        getAlbums()
    }

    fun getAlbums() = viewModelScope.launch {
        try {
            _albums.postValue(AlbumResponse(RetrofitStatus.SUCCESS, albumRepository.getAlbums()))
        } catch (e: Exception) {
            _albums.postValue(AlbumResponse(RetrofitStatus.FAILURE))
            e.printRetrofitError()
        }
    }
}