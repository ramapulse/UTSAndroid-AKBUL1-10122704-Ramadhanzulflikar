package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.Interest

@Dao
interface InterestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(interest: Interest)

    @Query("SELECT * FROM interest")
    fun getInterestsByProfileId(): LiveData<List<Interest>>

    @Update
    fun update(interest: Interest)

    @Delete
    fun delete(interest: Interest)
}