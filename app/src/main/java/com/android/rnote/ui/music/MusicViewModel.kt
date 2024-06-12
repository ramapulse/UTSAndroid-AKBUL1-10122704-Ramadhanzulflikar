package com.android.rnote.ui.music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.rnote.data.database.AppDatabase
import com.android.rnote.data.database.dao.MusicFavoriteDao
import com.android.rnote.data.database.dao.VideoFavoriteDao
import com.android.rnote.data.model.MusicFavorite
import com.android.rnote.data.model.VideoFavorite
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    private val musicFavoriteDao: MusicFavoriteDao = AppDatabase.getDatabase(application).musicFavoriteDao()

    private val videoFavoriteDao: VideoFavoriteDao = AppDatabase.getDatabase(application).videoFavoriteDao()

     fun getAllMusicFavorites(): LiveData<List<MusicFavorite>> {
        return musicFavoriteDao.getAllMusicFavorites()
    }

    fun insert(musicFavorite: MusicFavorite) = viewModelScope.launch {
        musicFavoriteDao.insert(musicFavorite)
    }

    fun update(musicFavorite: MusicFavorite) = viewModelScope.launch {
        musicFavoriteDao.update(musicFavorite)
    }

    fun delete(musicFavorite: MusicFavorite) = viewModelScope.launch {
        musicFavoriteDao.delete(musicFavorite)
    }

    fun getAllVideoFavorites(): LiveData<List<VideoFavorite>> {
        return videoFavoriteDao.getAllVideoFavorites()
    }

    fun insert(videoFavorite: VideoFavorite) = viewModelScope.launch {
        videoFavoriteDao.insert(videoFavorite)
    }

    fun update(videoFavorite: VideoFavorite) = viewModelScope.launch {
        videoFavoriteDao.update(videoFavorite)
    }

    fun delete(videoFavorite: VideoFavorite) = viewModelScope.launch {
        videoFavoriteDao.delete(videoFavorite)
    }


}