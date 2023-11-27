package com.mynotes.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mynotes.R
import com.mynotes.adapters.NotesAdapter
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class SavedNotesFragment: Fragment(R.layout.fragment_saved_notes) {

    lateinit var viewModel: NotesViewModel
    lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRecycleView()

        viewModel.getFavoritedNotes().observe(viewLifecycleOwner, Observer {notesList ->
            notesAdapter.differ.submitList(notesList)
        })
    }

    private fun initRecycleView() {
        notesAdapter = NotesAdapter()
        val rvLocalNotes = view?.findViewById<RecyclerView>(R.id.rvSavedNotes)
        rvLocalNotes?.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}