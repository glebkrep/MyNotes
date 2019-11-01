package com.example.mynotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.NoteInfo.NoteInfoActivity
import com.example.mynotes.database.Note
import com.example.mynotes.newNote.NewNoteActivity
import com.example.mynotes.noteList.NoteListAdapter
import com.example.mynotes.noteList.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


//TODO: add app icon
//TODO: make design more usable
//TODO: ?add firebase support

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private val newNoteActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setNotes(it)

            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }

    }


    //revieving transaction additional info
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                var name = it.getStringExtra("name")
                var content = it.getStringExtra("content")
                val note = Note(noteName = name,noteContent =content )

                noteViewModel.insert(note)
                Toast.makeText(this,"note created!",Toast.LENGTH_SHORT).show()
            }
        } else
        if (requestCode == NoteListAdapter.UPDATE_DELETE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //got from note info with success
            data?.let{
                val name = it.getStringExtra("name")
                val content = it.getStringExtra("content")
                val noteId = it.getStringExtra("id")
                noteViewModel.update(noteId.toInt(),name,content,System.currentTimeMillis())
                Toast.makeText(this,"note updated!",Toast.LENGTH_SHORT).show()
            }
        }else
            if (requestCode == NoteListAdapter.UPDATE_DELETE_REQUEST_CODE && resultCode == NoteInfoActivity.DELETE_RESULT_CODE){
                data?.let{
                    val noteId = it.getStringExtra("id")
                    noteViewModel.deleteById(noteId.toInt())
                    Toast.makeText(this,"note deleted!",Toast.LENGTH_SHORT).show()
                }
            }

        else if(requestCode == newNoteActivityRequestCode){
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.note_not_saved),
                        Toast.LENGTH_LONG).show()
                }

        else Toast.makeText(this,"note not updated\n(nothing is edited)",Toast.LENGTH_SHORT).show()

    }
}
