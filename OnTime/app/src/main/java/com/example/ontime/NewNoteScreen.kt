package com.example.ontime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ontime.database.NoteEntity
import kotlinx.android.synthetic.main.activity_new_note_screen.*
import java.util.*


class NewNoteScreen : AppCompatActivity() {

    val viewModel : NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note_screen)
        toolbarNewNote.setNavigationIcon(R.drawable.back_toolbar)
        setSupportActionBar(toolbarNewNote)
        

    }

    override fun onDestroy() {
        super.onDestroy()
        if (titleAdd.text.toString()!=""||bodyAdd.text.toString()!="") {
            val title: String = titleAdd.text.toString()
            val body: String = bodyAdd.text.toString()
            val d = Date()
            val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
            val note = NoteEntity(null, title, body, notesDate as String)
            viewModel.addNotes(note)
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Empty note", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_pin,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.pin_note->{



                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()

            }
            else->{
                return super.onOptionsItemSelected(item)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}