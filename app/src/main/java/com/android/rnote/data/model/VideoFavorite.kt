package com.android.rnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_favorite")
data class VideoFavorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val fileUrl: Int
)