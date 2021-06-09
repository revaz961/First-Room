package com.example.firstroom.database

import androidx.room.Room
import com.example.firstroom.App

object UserDatabase {
    val db = Room.databaseBuilder(
        App.context!!,
        AppDatabase::class.java, "database-name"
    ).build()
}