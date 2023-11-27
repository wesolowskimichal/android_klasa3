package com.mynotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mynotes.R
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NoteFragment: Fragment(R.layout.fragment_note) {

    lateinit var viewModel: NotesViewModel
    val args: NoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val note = args.note
        val title = view.findViewById<EditText>(R.id.etTitle)
        val context = view.findViewById<EditText>(R.id.etContext)
        val save = view.findViewById<RelativeLayout>(R.id.bSaveNote)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        val today: LocalDate = LocalDate.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate: String = today.format(formatter)
        title.setText(note.title)
        context.setText(note.content)
        title.addTextChangedListener {
            note.title = it.toString()
        }
        context.addTextChangedListener {
            note.content = it.toString()
        }
        save.setOnClickListener {
            note.modificationDate = formattedDate
            viewModel.saveNote(note)
            Snackbar.make(view, "Note has been saved", Snackbar.LENGTH_SHORT).show()
        }
        fab.setOnClickListener {
            if(note.id != null) {
                viewModel.updateIsFavorited(note.id!!, !note.isFavorited)
                if(note.isFavorited) {
                    Snackbar.make(view, "Note deleted from favorited", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, "Note added to favorited", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}