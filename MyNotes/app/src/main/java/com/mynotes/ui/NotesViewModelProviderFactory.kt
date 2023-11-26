package com.mynotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mynotes.repository.NotesRepository

class NotesViewModelProviderFactory(
    val notesRepository: NotesRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}