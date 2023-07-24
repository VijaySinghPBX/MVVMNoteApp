package com.example.note.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note.database.NoteDatabase
import com.example.note.models.Note
import com.example.note.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repo: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getDao()
        repo = NoteRepository(dao)
        allNotes = repo.allNotes
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateNote(note)
    }
}