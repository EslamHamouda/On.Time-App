package com.example.ontime.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var title:String,
    var body:String,
    var date:String
)



