package com.example.mynotes.noteList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.MainActivity
import com.example.mynotes.NoteInfo.NoteInfoActivity
import com.example.mynotes.R
import com.example.mynotes.database.Note
import com.example.mynotes.newNote.NewNoteActivity

class NoteListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of words

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteNameItemView: TextView = itemView.findViewById(R.id.noteNameTextView)
        val noteContentItemView: TextView = itemView.findViewById(R.id.noteContentTextView)
        val noteDateItemView: TextView = itemView.findViewById(R.id.noteDateTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NoteInfoActivity::class.java)
            intent.putExtra("id",current.id)
            intent.putExtra("name", current.noteName)
            intent.putExtra("content", current.noteContent)
            intent.putExtra("date", current.noteDate)
            (context as Activity).startActivityForResult(intent,UPDATE_DELETE_REQUEST_CODE)
        }

        holder.noteNameItemView.text = current.noteName
        holder.noteContentItemView.text = current.noteContent

        holder.noteDateItemView.text = Utils.millisToDate(current.noteDate)


    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size

    companion object{
        val UPDATE_DELETE_REQUEST_CODE = 2
    }
}