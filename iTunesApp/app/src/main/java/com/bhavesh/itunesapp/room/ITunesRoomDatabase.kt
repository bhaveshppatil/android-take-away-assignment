package com.masai.movieapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//database class which returns the instance of our database
@Database(entities = [ITunesTable::class], version = 1)
abstract class ITunesRoomDatabase : RoomDatabase() {

    abstract fun getDataFromDB(): ITunesDAO

}