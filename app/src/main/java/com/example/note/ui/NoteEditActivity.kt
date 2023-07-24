package com.example.note.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.models.Note
import com.example.note.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date

class NoteEditActivity : AppCompatActivity() {
    private lateinit var noteTitleEd: EditText
    private lateinit var noteDescriptionEd: EditText
    private lateinit var saveButton: Button
    private lateinit var viewModel: NoteViewModel
    private var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)
        noteTitleEd = findViewById(R.id.add_note_title)
        noteDescriptionEd = findViewById(R.id.add_note_description)
        saveButton = findViewById(R.id.save_btn)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val title = intent.getStringExtra("noteTitle")
            val des = intent.getStringExtra("noteDes")
            noteId = intent.getIntExtra("noteId", -1)
            noteTitleEd.setText(title)
            noteDescriptionEd.setText(des)
            saveButton.text = "Update Note"
        } else {
            saveButton.text = "Save Button"
        }
        saveButton.setOnClickListener {
            val noteTitle = noteTitleEd.text.toString()
            val noteDes = noteDescriptionEd.text.toString()
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDes.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateTime = sdf.format(Date())
                    val updatedNote = Note(
                        noteTitle,
                        noteDes,
                        "Updated At $currentDateTime",
                        noteId
                    )
                    viewModel.updateNote(updatedNote)
                    val toast = Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT)
                    val view = toast.view
                    view?.setBackgroundResource(android.R.color.transparent)
                    toast.show()
                    returnBack()
                } else
                    Toast.makeText(this, "Enter Valid Note", Toast.LENGTH_SHORT).show()
            } else {
                if (noteTitle.isNotEmpty() && noteDes.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateTime = sdf.format(Date())
                    val note = Note(
                        noteTitle,
                        noteDes,
                        "Updated At $currentDateTime"
                    )
                    viewModel.addNote(note)
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
                    returnBack()
                } else
                    Toast.makeText(this, "Enter Valid Note", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun returnBack() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }
}