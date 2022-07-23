package com.example.ontime.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(tableName = "Events")
data class EventEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var title:String,
    var startFrom:String,
    var finish:String,
    var repeat:Int,
    var reminder:String,
    var place:String,
    var notes:String
)