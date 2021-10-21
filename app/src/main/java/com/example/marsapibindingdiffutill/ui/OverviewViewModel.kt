package com.example.marsapibindingdiffutill.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsapibindingdiffutill.api.RetrofitInstance
import com.example.marsapibindingdiffutill.model.MarsPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarsPhotoStatus { LOADING, DONE, ERROR }

class OverviewViewModel : ViewModel() {
    private val TAG = "OverviewViewModel"

    private val _status = MutableLiveData<MarsPhotoStatus>()
    val status: LiveData<MarsPhotoStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos: LiveData<List<MarsPhoto>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        MarsPhotoStatus.LOADING
        try {
            viewModelScope.launch {
                _photos.value = RetrofitInstance.api.getPhotos()
                _status.value = MarsPhotoStatus.DONE
            }
        } catch (e: Exception) {
            _status.value = MarsPhotoStatus.ERROR
            _photos.value = listOf()
            Log.d(TAG, "${e.message}")
        }
    }
}

