package com.base.androidappwithcompose.data.room

import android.content.Context
import androidx.room.Room

class Room {

    fun initRoom(context: Context){
        roomDatabase=Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object{
        lateinit var roomDatabase: AppDatabase
    }
}