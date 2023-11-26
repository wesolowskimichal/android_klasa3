package com.mynotes.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mynotes.R
import com.mynotes.db.NoteDatabase
import com.mynotes.models.Note
import com.mynotes.repository.NotesRepository


class MainActivity : AppCompatActivity() {
    // navigation
    private lateinit var bnv: BottomNavigationView
    private lateinit var nhf: NavHostFragment

    //mvvm
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesRepository = NotesRepository(NoteDatabase(this))
        val viewModelProviderFactory = NotesViewModelProviderFactory(notesRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NotesViewModel::class.java)
//        viewModel.saveNote(Note(1, "tytul", "aasdsadasdasdsad", "26.11.20.23", true))
//        viewModel.saveNote(Note(2, "tytul2", "aasdsadasdasdsad", "26.11.20.23", false))
//        viewModel.saveNote(Note(3, "tytul3", "aasdsadasdasdsad", "26.11.20.23", true))

        setContentView(R.layout.activity_main)
        bnv = findViewById(R.id.bottomNavigationView)
        nhf = supportFragmentManager.findFragmentById(R.id.notesNavHostFragment) as NavHostFragment
        bnv.setupWithNavController(nhf.findNavController())
    }
}