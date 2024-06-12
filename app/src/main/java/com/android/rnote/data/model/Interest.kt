package com.android.rnote.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "interest",
)
data class Interest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hobby: String,
    val makes: String,
    val mikes: String,
    val interest: String,
    val goal: String
)