package com.mynotes.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mynotes.R
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class NoteFragment: Fragment(R.layout.fragment_note) {

    lateinit var viewModel: NotesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}