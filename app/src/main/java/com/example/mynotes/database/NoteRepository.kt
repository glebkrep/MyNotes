package com.example.mynotes.database

import android.util.Log
import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDao: NoteDao){

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotesDesc()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteById(id:Int){
        noteDao.deleteById(id)
    }

    suspend fun update(id:Int,newName:String,newContent:String,newDate:Long){
        noteDao.update(id,newName,newContent,newDate)
        Log.i("NoteRepository","$id\n$newName\n$newContent\n$newDate")
    }

}