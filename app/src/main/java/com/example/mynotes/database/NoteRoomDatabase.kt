package com.example.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Name class
@Database(entities = arrayOf(Note::class), version = 2)
public abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        // volatile - writes to that property will be immediately seen on other threads
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context,scope:CoroutineScope): NoteRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database"
                )
                    //callback created later in this file
//                    .addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


//    //call back to empty db and add some enteties on creation
//    private class NoteDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    populateDatabase(database.noteDao())
//                }
//            }
//        }
//
//        suspend fun populateDatabase(noteDao: NoteDao) {
//            // Delete all content here.
//            noteDao.deleteAll()
//
//            // Add sample notes.
//            var note = Note(noteName = "First note",noteContent = "First note content")
//            noteDao.insert(note)
//            note = Note(noteContent = "no name second note")
//            noteDao.insert(note)
//
//        }
//    }
}