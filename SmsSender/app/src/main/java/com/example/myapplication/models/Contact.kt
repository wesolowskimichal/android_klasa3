package com.example.myapplication.models

data class Contact(
    val name: String,
    val number: String,
    var selected: Boolean = false
)