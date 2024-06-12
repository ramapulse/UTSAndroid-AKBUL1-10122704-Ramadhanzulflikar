package com.android.rnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey()
    val id: Int = 0,
    val name: String,
    val photoUrl: String,
    val description: String,
    val phoneNumber: String,
    val email: String,
    val socialMediaLink: String,
    val latitude: Double,
    val longitude: Double
)