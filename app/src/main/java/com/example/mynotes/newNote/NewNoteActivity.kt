package com.example.mynotes.newNote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.mynotes.R

class NewNoteActivity : AppCompatActivity() {

    private lateinit var editNoteNameView: EditText
    private lateinit var editNoteContentView: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)


        editNoteNameView = findViewById(R.id.edit_name)
        editNoteContentView = findViewById(R.id.edit_content)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNoteContentView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNoteNameView.text.toString()
                val content = editNoteContentView.text.toString()
                replyIntent.putExtra("name", name)
                replyIntent.putExtra("content",content)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }


    }

}
