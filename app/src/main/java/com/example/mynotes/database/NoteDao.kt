package com.example.mynotes.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Query("select * from note_table order by note_date_milli desc")
    fun getAllNotesDesc(): LiveData<List<Note>>

    @Insert
    suspend fun insert(note:Note)

    @Query("UPDATE note_table SET note_name = :newName, note_content = :newContent, note_date_milli = :newDate WHERE id = :id")
    suspend fun update(id:Int,newName:String,newContent:String,newDate:Long)

    @Query("delete from note_table where id = :id")
    suspend fun deleteById(id:Int)

    @Query("delete from note_table")
    suspend fun deleteAll()
}