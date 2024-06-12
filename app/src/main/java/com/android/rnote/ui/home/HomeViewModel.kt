package com.android.rnote.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.rnote.data.database.AppDatabase
import com.android.rnote.data.database.dao.InterestDao
import com.android.rnote.data.database.dao.ProfileDao
import com.android.rnote.data.model.Interest
import com.android.rnote.data.model.Profile
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val interestDao: InterestDao = AppDatabase.getDatabase(application).interestDao()
    private val profileDao: ProfileDao = AppDatabase.getDatabase(application).profileDao()

    fun getProfileById(): LiveData<Profile> {
        return profileDao.getProfileById()
    }

    fun getInterestsByProfileId(): LiveData<List<Interest>> {
        return interestDao.getInterestsByProfileId()
    }

    fun insert(interest: Interest) = viewModelScope.launch {
        interestDao.insert(interest)
    }

    fun update(interest: Interest) = viewModelScope.launch {
        interestDao.update(interest)
    }

    fun delete(interest: Interest) = viewModelScope.launch {
        interestDao.delete(interest)
    }
}