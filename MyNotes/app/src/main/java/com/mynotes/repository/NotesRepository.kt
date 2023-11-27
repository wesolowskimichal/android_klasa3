package com.mynotes.repository

import androidx.lifecycle.LiveData
import com.mynotes.db.NoteDatabase
import com.mynotes.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepository(
    val db: NoteDatabase
) {
    suspend fun upsert(note: Note) = db.getNoteDao().upsert(note)

    suspend fun updateIsFavorited(noteId: Int, isFavorited: Boolean) = db.getNoteDao().updateIsFavorited(noteId, isFavorited)

    suspend fun searchNotesByTitle(searchTitle: String) = withContext(Dispatchers.IO) {
        db.getNoteDao().searchNotesByTitle(searchTitle)
    }

    fun getSavedNotes() = db.getNoteDao().getAllNotes()

    fun getFavoritedNotes() = db.getNoteDao().getFavoritedNotes()
    fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
}