package com.android.rnote.ui.daily

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.rnote.data.database.AppDatabase
import com.android.rnote.data.database.dao.DailyActivityDao
import com.android.rnote.data.database.dao.FriendDao
import com.android.rnote.data.model.DailyActivity
import com.android.rnote.data.model.Friend
import kotlinx.coroutines.launch

class DailyViewModel(application: Application) : AndroidViewModel(application)  {
    private val dailyActivityDao: DailyActivityDao = AppDatabase.getDatabase(application).dailyActivityDao()

    private val friendDao: FriendDao = AppDatabase.getDatabase(application).friendDao()

    fun getAllDailyActivities(): LiveData<List<DailyActivity>> {
        return dailyActivityDao.getAllDailyActivities()
    }

    fun insert(dailyActivity: DailyActivity) = viewModelScope.launch {
        dailyActivityDao.insert(dailyActivity)
    }

    fun update(dailyActivity: DailyActivity) = viewModelScope.launch {
        dailyActivityDao.update(dailyActivity)
    }

    fun delete(dailyActivity: DailyActivity) = viewModelScope.launch {
        dailyActivityDao.delete(dailyActivity)
    }

    fun getAllFriends(): LiveData<List<Friend>> {
        return friendDao.getAllFriends()
    }

    fun insert(friend: Friend) = viewModelScope.launch {
        friendDao.insert(friend)
    }

    fun update(friend: Friend) = viewModelScope.launch {
        friendDao.update(friend)
    }

    fun delete(friend: Friend) = viewModelScope.launch {
        friendDao.delete(friend)
    }

}