package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.MusicFavorite

@Dao
interface MusicFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(musicFavorite: MusicFavorite)

    @Query("SELECT * FROM music_favorite")
    fun getAllMusicFavorites(): LiveData<List<MusicFavorite>>

    @Update
    fun update(musicFavorite: MusicFavorite)

    @Delete
    fun delete(musicFavorite: MusicFavorite)
}
