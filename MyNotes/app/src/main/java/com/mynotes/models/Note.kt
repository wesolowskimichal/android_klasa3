package com.mynotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes"
)
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val content: String,
    val modificationDate: String
)