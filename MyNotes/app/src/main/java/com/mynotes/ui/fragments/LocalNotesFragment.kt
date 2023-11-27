package com.mynotes.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mynotes.R
import com.mynotes.adapters.NotesAdapter
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class LocalNotesFragment: Fragment(R.layout.fragment_local_notes) {

    lateinit var viewModel: NotesViewModel
    lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRecycleView()

        notesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("note", it)
            }

            findNavController().navigate(
                R.id.action_localNotesFragment_to_noteFragment,
                bundle
            )
        }

        val addNoteButton = view.findViewById<FloatingActionButton>(R.id.fab_addNote)
        addNoteButton.setOnClickListener {
            // Create a new note instance and navigate to the NoteFragment
            val newNote =
                com.mynotes.models.Note(title = "New Note", content = " ", modificationDate = "19.09.1999", isFavorited = false) // Replace Note() with your actual Note class instantiation logic
            val bundle = Bundle().apply {
                putSerializable("note", newNote)
            }

            findNavController().navigate(
                R.id.action_localNotesFragment_to_noteFragment,
                bundle
            )
        }

        viewModel.getSavedNotes().observe(viewLifecycleOwner, Observer { notesList ->
            notesAdapter.differ.submitList(notesList)
        })
    }

    private fun initRecycleView() {
        notesAdapter = NotesAdapter()
        val rvLocalNotes = view?.findViewById<RecyclerView>(R.id.rvLocalNotes)
        rvLocalNotes?.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}