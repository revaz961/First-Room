package com.example.firstroom

import androidx.room.Room

object UserDatabase {
    val db = Room.databaseBuilder(
        App.context!!,
        AppDatabase::class.java, "database-name"
    ).build()
}