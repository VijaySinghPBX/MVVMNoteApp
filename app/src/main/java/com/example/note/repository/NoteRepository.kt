package com.example.note.repository

import com.example.note.database.NoteDao
import com.example.note.models.Note

class NoteRepository(private val dao: NoteDao) {

    val allNotes = dao.getAllNotes()

    suspend fun insertNote(note: Note) {
        dao.insert(note)
    }

    suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }

    suspend fun updateNote(note: Note) {
        dao.update(note)
    }
}