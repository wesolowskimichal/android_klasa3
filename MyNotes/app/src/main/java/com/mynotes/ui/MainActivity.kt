package com.mynotes.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mynotes.R


class MainActivity : AppCompatActivity() {
    private lateinit var bnv: BottomNavigationView
    private lateinit var nvf: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bnv = findViewById(R.id.bottomNavigationView)
        nvf = supportFragmentManager.findFragmentById(R.id.notesNavHostFragment) as NavHostFragment
        bnv.setupWithNavController(nvf.findNavController())
    }
}