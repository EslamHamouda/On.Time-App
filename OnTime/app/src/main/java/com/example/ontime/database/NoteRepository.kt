package com.example.ontime.database

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {




    fun getAllNotes():LiveData<List<NoteEntity>>{
        return notesDao.getNotes()
    }

     fun insert(note: NoteEntity) {
        notesDao.insertNotes(note)
    }

     fun delete(note: Int){
        notesDao.deleteNotes(note)
    }

     fun update(note: NoteEntity){
        notesDao.updateNotes(note)
    }


    fun getAllEvents():LiveData<List<EventEntity>>{
        return notesDao.getEvents()
    }

    fun insertEvent(event: EventEntity) {
        notesDao.insertEvent(event)
    }

    fun deleteEvent(event: Int){
        notesDao.deleteEvent(event)
    }

    fun updateEvent(event: EventEntity){
        notesDao.updateEvent(event)
    }

}