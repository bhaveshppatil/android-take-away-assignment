package com.bhavesh.itunesapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity which represents the database in form of table
@Entity(tableName = "itunes_table")
data class ITunesTable(
    @ColumnInfo(name = "trackName") val trackName: String,
    @ColumnInfo(name = "artistName") var artistName: String,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String,
    @ColumnInfo(name = "trackViewUrl") val trackViewUrl: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}