package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM profile WHERE id = 0")
    fun getProfileById(): LiveData<Profile>

    @Update
    fun update(profile: Profile)

    @Delete
    fun delete(profile: Profile)
}
