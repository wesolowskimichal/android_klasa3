package com.mynotes.ui

import androidx.lifecycle.ViewModel
import com.mynotes.repository.NotesRepository

class NotesViewModel(
    val notesRepository: NotesRepository
): ViewModel() {

}