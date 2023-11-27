package com.mynotes.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynotes.models.Note
import com.mynotes.repository.NotesRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotesViewModel(
    val notesRepository: NotesRepository
): ViewModel() {
    private var _searchNotes: MutableLiveData<List<Note>> = MutableLiveData()
    val searchNotes: LiveData<List<Note>>
        get() = _searchNotes

    suspend fun saveNote(note: Note):Long {
        return notesRepository.upsert(note)
    }
    fun updateIsFavorited(noteId: Int, isFavorited: Boolean) = viewModelScope.launch {
        notesRepository.updateIsFavorited(noteId, isFavorited)
    }
    fun searchNotesByTitle(searchTitle: String) = viewModelScope.launch {
        val resposne = notesRepository.searchNotesByTitle(searchTitle)
        _searchNotes.postValue(resposne)

    }

    fun getIsFavoritedFromNoteById(noteID: Int) = notesRepository.getIsFavoritedFromNoteById(noteID)

    fun getSavedNotes() = notesRepository.getSavedNotes()
    fun getFavoritedNotes() = notesRepository.getFavoritedNotes()
    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }

    fun getCurrentDate(): String {
        val today: LocalDate = LocalDate.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate: String = today.format(formatter)
        return formattedDate
    }
}