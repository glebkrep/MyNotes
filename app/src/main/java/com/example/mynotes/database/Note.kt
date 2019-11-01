package com.example.mynotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int=0,

    @ColumnInfo(name = "note_name")
    var noteName:String = "",

    @ColumnInfo(name = "note_content")
    var noteContent:String,

    @ColumnInfo(name = "note_date_milli")
    var noteDate:Long = System.currentTimeMillis()

)