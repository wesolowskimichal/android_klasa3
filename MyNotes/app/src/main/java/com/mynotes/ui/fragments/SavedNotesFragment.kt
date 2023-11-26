package com.mynotes.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mynotes.R
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class SavedNotesFragment: Fragment(R.layout.fragment_saved_notes) {

    lateinit var viewModel: NotesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}