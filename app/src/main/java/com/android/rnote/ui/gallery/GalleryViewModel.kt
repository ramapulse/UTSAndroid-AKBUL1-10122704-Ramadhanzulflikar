package com.android.rnote.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.rnote.data.database.AppDatabase
import com.android.rnote.data.database.dao.GalleryDao
import com.android.rnote.data.model.Gallery
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val galleryDao: GalleryDao = AppDatabase.getDatabase(application).galleryDao()

    fun getAllGalleryItems(): List<Gallery> {
        return galleryDao.getAllGalleryItems()
    }

    fun insert(gallery: Gallery) = viewModelScope.launch {
        galleryDao.insert(gallery)
    }

    fun update(gallery: Gallery) = viewModelScope.launch {
        galleryDao.update(gallery)
    }

    fun delete(gallery: Gallery) = viewModelScope.launch {
        galleryDao.delete(gallery)
    }
}
