package com.mynotes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mynotes.R
import com.mynotes.adapters.NotesAdapter
import com.mynotes.ui.MainActivity
import com.mynotes.ui.NotesViewModel

class SearchNoteFragment: Fragment(R.layout.fragment_search_notes) {

    lateinit var viewModel: NotesViewModel
    lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRecycleView()

        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener {
            it?.let {
                if(it.toString().isNotEmpty()) {
                    viewModel.searchNotesByTitle(it.toString())
                }
            }
        }

        viewModel.searchNotes.observe(viewLifecycleOwner, Observer { notesList ->
            notesAdapter.differ.submitList(notesList)
        })
    }

    private fun initRecycleView() {
        notesAdapter = NotesAdapter()
        val rvLocalNotes = view?.findViewById<RecyclerView>(R.id.rvSearchNotes)
        rvLocalNotes?.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}