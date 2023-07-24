package com.example.note.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_table"
)
data class Note(
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_des")
    val description: String,
    @ColumnInfo(name = "note_time_stamp")
    val timeStamp: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)
