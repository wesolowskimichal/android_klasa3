package com.mynotes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mynotes.models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: Note): Long

    @Query("UPDATE notes SET isFavorited = :isFavorited WHERE id = :noteId")
    suspend fun updateIsFavorited(noteId: Int, isFavorited: Boolean)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchTitle || '%'")
    fun searchNotesByTitle(searchTitle: String): List<Note>

    @Query("SELECT * FROM notes where isFavorited = 1")
    fun getFavoritedNotes(): LiveData<List<Note>>

    @Query("SELECT isFavorited from notes where id = :noteID")
    fun getIsFavoritedFromNoteById(noteID: Int): LiveData<Boolean>

    @Delete
    fun deleteNote(note: Note)
}