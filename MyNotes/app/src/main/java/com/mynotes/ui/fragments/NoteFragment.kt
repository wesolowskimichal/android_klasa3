package com.mynotes.ui.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mynotes.R
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel
import kotlinx.coroutines.launch
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
        val goBack = view.findViewById<RelativeLayout>(R.id.bGoBack)
        val deleteNote = view.findViewById<RelativeLayout>(R.id.bDeleteNote)

        title.setText(note.title)
        context.setText(note.content)
        title.addTextChangedListener {
            note.title = it.toString()
        }
        context.addTextChangedListener {
            note.content = it.toString()
        }
        save.setOnClickListener {
            lifecycleScope.launch {
                hideKeyboard(view)
                note.modificationDate = viewModel.getCurrentDate()
                viewModel.saveNote(note)
                Snackbar.make(view, "Note has been saved", Snackbar.LENGTH_SHORT).show()
            }
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

        note.id?.let {
            viewModel.getIsFavoritedFromNoteById(it).observe(viewLifecycleOwner, Observer {isFavorited ->
                if(isFavorited) {
                    fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.fab_favorite))
                } else {
                    fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.fab_not_favorite))
                }
            })
        }

        deleteNote.setOnClickListener {
            viewModel.deleteNote(note)
            findNavController().navigate(
                R.id.action_noteFragment_to_localNotesFragment
            )
        }

        goBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_noteFragment_to_localNotesFragment
            )
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}