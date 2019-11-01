package com.example.mynotes.NoteInfo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mynotes.R
import com.example.mynotes.newNote.NewNoteActivity
import com.example.mynotes.noteList.Utils
import kotlinx.android.synthetic.main.activity_note_info.*

class NoteInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_info)

        val noteNameEditText: EditText = findViewById(R.id.noteNameInfoEditText)
        val noteContentEditText: EditText = findViewById(R.id.noteContentInfoEditText)
        val noteDateItemView: TextView = findViewById(R.id.noteDateInfoTextView)


        noteNameEditText.setText(intent!!.extras!!["name"].toString())
        noteContentEditText.setText(intent!!.extras!!["content"].toString())
        noteDateItemView.text = Utils.millisToDate(intent!!.extras!!["date"].toString().toLong())

        val noteId = intent!!.extras!!["id"]
        noteInfoDeleteButton.setOnClickListener {

            val replyIntent = Intent()
            replyIntent.putExtra("id",noteId.toString())
            setResult(DELETE_RESULT_CODE, replyIntent)
            finish()
        }


        noteInfoUpdateButton.setOnClickListener {
            val replyIntent = Intent()
            if (noteContentEditText.text.toString() == intent!!.getStringExtra("content") && noteNameEditText.text.toString() == intent!!.getStringExtra("name")) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = noteNameEditText.text.toString()
                val content = noteContentEditText.text.toString()
                replyIntent.putExtra("name", name)
                replyIntent.putExtra("content", content)
                replyIntent.putExtra("id",noteId.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }

    }
    companion object{
        val DELETE_RESULT_CODE = 666
    }
}
