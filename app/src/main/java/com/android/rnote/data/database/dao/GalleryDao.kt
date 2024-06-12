package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.Gallery

@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gallery: Gallery)

    @Query("SELECT * FROM gallery")
    fun getAllGalleryItems(): List<Gallery>

    @Update
    fun update(gallery: Gallery)

    @Delete
    fun delete(gallery: Gallery)
}
