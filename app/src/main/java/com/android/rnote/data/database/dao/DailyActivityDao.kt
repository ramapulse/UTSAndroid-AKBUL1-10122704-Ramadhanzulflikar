package com.android.rnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.rnote.data.model.DailyActivity

@Dao
interface DailyActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyActivity: DailyActivity)

    @Query("SELECT * FROM daily_activity")
    fun getAllDailyActivities(): LiveData<List<DailyActivity>>

    @Update
    fun update(dailyActivity: DailyActivity)

    @Delete
    fun delete(dailyActivity: DailyActivity)
}
