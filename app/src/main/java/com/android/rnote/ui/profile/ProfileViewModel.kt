package com.android.rnote.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.rnote.data.database.AppDatabase
import com.android.rnote.data.database.dao.InterestDao
import com.android.rnote.data.database.dao.ProfileDao
import com.android.rnote.data.model.Profile
import com.android.rnote.data.model.ProfileWithInterests
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val profileDao: ProfileDao = AppDatabase.getDatabase(application).profileDao()

    fun getProfileById(): LiveData<Profile> {
        return profileDao.getProfileById()
    }

    fun insert(profile: Profile) = viewModelScope.launch {
        profileDao.insert(profile)
    }

    fun update(profile: Profile) = viewModelScope.launch {
        profileDao.update(profile)
    }

    fun delete(profile: Profile) = viewModelScope.launch {
        profileDao.delete(profile)
    }
}


