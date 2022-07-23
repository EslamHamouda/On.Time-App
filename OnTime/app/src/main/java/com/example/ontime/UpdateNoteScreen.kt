package com.example.ontime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ontime.database.NoteEntity
import kotlinx.android.synthetic.main.activity_new_note_screen.*
import kotlinx.android.synthetic.main.activity_update_note_screen.*
import java.util.*


class UpdateNoteScreen : AppCompatActivity() {

    val viewModel : NoteViewModel by viewModels()
    var a:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note_screen)
        toolbarUpdateNote.setNavigationIcon(R.drawable.back_toolbar)
        setSupportActionBar(toolbarUpdateNote)


        a= intent.extras?.get("id") as Int
        var b:String= intent.extras?.get("title").toString()
        var c:String= intent.extras?.get("body").toString()
        var e1:EditText=titleAddUpdate.findViewById(R.id.titleAddUpdate)
        var e2:EditText=bodyAddUpdate.findViewById(R.id.bodyAddUpdate)
        e1.setText(b)
        e2.setText(c)
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        viewModel.updateNotes(NoteEntity(a,e1.text.toString(),e2.text.toString(),
            notesDate as String
        ))

    }

    override fun onDestroy() {
        super.onDestroy()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        viewModel.updateNotes(NoteEntity(a,titleAddUpdate.text.toString(),bodyAddUpdate.text.toString(),
            notesDate as String
        ))
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