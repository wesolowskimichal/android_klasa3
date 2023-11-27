package com.mynotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mynotes.R
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class NoteFragment: Fragment(R.layout.fragment_note) {

    lateinit var viewModel: NotesViewModel
    val args: NoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val note = args.note
        val title = view.findViewById<EditText>(R.id.etTitle)
        val context = view.findViewById<EditText>(R.id.etContext)
        val save = view.findViewById<Button>(R.id.bSaveNote)
        title.setText(note.title)
        context.setText(note.content)
        title.addTextChangedListener {
            note.title = it.toString()
        }
        context.addTextChangedListener {
            note.content = it.toString()
        }
        save.setOnClickListener {
            viewModel.saveNote(note)
        }
    }
}