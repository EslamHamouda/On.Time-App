package com.example.ontime.database

import androidx.lifecycle.LiveData
import androidx.room.*


        @Dao
        interface NotesDao {
                @Query("SELECT * FROM Notes")
                fun getNotes():LiveData<List<NoteEntity>>

                @Insert(onConflict = OnConflictStrategy.REPLACE)
                fun insertNotes(notes: NoteEntity)

                @Query("DELETE FROM Notes WHERE id=:id")
                fun deleteNotes(id:Int)

                @Update
                fun updateNotes(notes: NoteEntity)


                @Query("SELECT * FROM Events")
                fun getEvents():LiveData<List<EventEntity>>

                @Insert(onConflict = OnConflictStrategy.REPLACE)
                fun insertEvent(event: EventEntity)

                @Query("DELETE FROM Events WHERE id=:id")
                fun deleteEvent(id:Int)

                @Update
                fun updateEvent(event: EventEntity)
        }


