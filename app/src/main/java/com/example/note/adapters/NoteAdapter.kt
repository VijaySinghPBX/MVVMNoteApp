package com.example.note.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.models.Note

class NoteAdapter(private val listener: NoteClickListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private val notes = ArrayList<Note>()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.note_title)
        val noteDelete: ImageView = itemView.findViewById(R.id.note_delete)
        val noteTime: TextView = itemView.findViewById(R.id.note_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.apply {
            noteTitle.text = currentNote.title
            noteTime.text = currentNote.timeStamp
            noteDelete.setOnClickListener {
                listener.onDeleteNoteClick(currentNote)
            }
            itemView.setOnClickListener {
                listener.onNoteClick(currentNote)
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateNote(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickListener{
    fun onDeleteNoteClick(note: Note)
    fun onNoteClick(note: Note)
}