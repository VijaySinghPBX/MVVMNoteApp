package com.example.note.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.adapters.NoteAdapter
import com.example.note.adapters.NoteClickListener
import com.example.note.models.Note
import com.example.note.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickListener {
    private lateinit var addButton: FloatingActionButton
    private lateinit var noteList: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton = findViewById(R.id.note_add_btn)
        noteList = findViewById(R.id.note_rv)
        noteList.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(this)
        noteList.adapter = noteAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, Observer { list ->
            list.let {
                noteAdapter.updateNote(it)
            }
        })

        addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteEditActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteNoteClick(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this, NoteEditActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteId", note.id)
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDes", note.description)
        startActivity(intent)
        this.finish()
    }
}