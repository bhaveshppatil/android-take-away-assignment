package com.masai.movieapp.room

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object
@Dao
interface ITunesDAO {

    //adds the list into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(ITunesTable: ITunesTable)

    @Query("select * from itunes_table")
    fun getDataFromDB(): LiveData<ITunesTable>

    @Query("delete from itunes_table")
    fun deleteDataFromDB()

}