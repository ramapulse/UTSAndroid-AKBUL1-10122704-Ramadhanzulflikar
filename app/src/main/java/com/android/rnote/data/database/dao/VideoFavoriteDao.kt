package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.VideoFavorite

@Dao
interface VideoFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(videoFavorite: VideoFavorite)

    @Query("SELECT * FROM video_favorite")
    fun getAllVideoFavorites(): LiveData<List<VideoFavorite>>

    @Update
    fun update(videoFavorite: VideoFavorite)

    @Delete
    fun delete(videoFavorite: VideoFavorite)
}
