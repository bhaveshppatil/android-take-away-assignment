package com.bhavesh.itunesapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhavesh.itunesapp.room.ITunesDAO
import com.bhavesh.itunesapp.room.ITunesTable

//database class which returns the instance of our database
@Database(entities = [ITunesTable::class], version = 1)
abstract class ITunesRoomDatabase : RoomDatabase() {

    abstract fun getDataFromDB(): ITunesDAO

}