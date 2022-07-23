package com.example.ontime

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ontime.database.EventEntity
import com.example.ontime.database.NoteDatabase
import com.example.ontime.database.NoteEntity
import com.example.ontime.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



 class NoteViewModel(application: Application):AndroidViewModel(application) {
    var repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository= NoteRepository(dao)
    }

    fun addNotes(notes: NoteEntity){
        repository.insert(notes)
    }
    fun getNotes():LiveData<List<NoteEntity>> {
        return repository.getAllNotes()
    }
    fun deleteNotes(id:Int){
        repository.delete(id)
    }
    fun updateNotes(notes: NoteEntity){
        repository.update(notes)
    }

     fun addEvent(event: EventEntity){
         repository.insertEvent(event)
     }
     fun getEvents():LiveData<List<EventEntity>> {
         return repository.getAllEvents()
     }
     fun deleteEvent(id:Int){
         repository.deleteEvent(id)
     }
     fun updateEvent(event: EventEntity){
         repository.updateEvent(event)
     }
}

