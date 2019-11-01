package com.example.mynotes.noteList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotes.database.Note
import com.example.mynotes.database.NoteRepository
import com.example.mynotes.database.NoteRoomDatabase
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NoteRepository
    // LiveData gives us updated words when they change.
    val allNotes: LiveData<List<Note>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        //adding scope to launch on vm scope
        val notesDao = NoteRoomDatabase.getDatabase(application,viewModelScope).noteDao()
        repository = NoteRepository(notesDao)
        allNotes = repository.allNotes
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun deleteById(id:Int) = viewModelScope.launch {
        repository.deleteById(id)
    }
    fun update(id:Int,newName:String,newContent:String,newDate:Long) = viewModelScope.launch {
        repository.update(id,newName,newContent,newDate)
    }
}

