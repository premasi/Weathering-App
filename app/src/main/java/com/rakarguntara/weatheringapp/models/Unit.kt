package com.rakarguntara.weatheringapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class Unit(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name ="unit")
    val unit: String
)
