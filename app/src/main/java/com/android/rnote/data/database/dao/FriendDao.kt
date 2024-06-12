package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.Friend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(friend: Friend)

    @Query("SELECT * FROM friends")
    fun getAllFriends(): LiveData<List<Friend>>

    @Update
    fun update(friend: Friend)

    @Delete
    fun delete(friend: Friend)
}