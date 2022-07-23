package com.example.ontime

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ontime.database.NoteEntity


class RecyclerNoteAdabter(val context: Context,
                          var notesList: ArrayList<NoteEntity>) :RecyclerView.Adapter<RecyclerNoteAdabter.NotesViewHolder>() {

    class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.Title)
        val body: TextView = itemView.findViewById(R.id.Body)
        val date: TextView = itemView.findViewById(R.id.noteDate)
        val linear: LinearLayout = itemView.findViewById(R.id.linear)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent, false
        )
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        var a:String=notesList[position].title
        var b:String=notesList[position].body
        var c:String=notesList[position].date

        holder.title.text = a
        holder.body.text = b
        holder.date.text = c

        holder.linear.setOnClickListener{
            val intent=Intent(context,UpdateNoteScreen::class.java)
            intent.putExtra("id",notesList[position].id)
            intent.putExtra("title",notesList[position].title)
            intent.putExtra("body",notesList[position].body)
            it.context.startActivity(intent)
        }

        /* holder.binding.root.setOnClickListener {
             val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(notesList[position])
             Navigation.findNavController(it).navigate(action)
         }*/

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilteredList:ArrayList<NoteEntity>){
        notesList=newFilteredList
        notifyDataSetChanged()
    }
}













/*class RecyclerNoteAdabter(
    val context: Context,
    var allNotes: List<NoteEntity>
) :
    RecyclerView.Adapter<RecyclerNoteAdabter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: EditText = itemView.findViewById(R.id.textTitle)
        val body: EditText = itemView.findViewById(R.id.textBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.setText(allNotes[position].title)
        holder.body.setText(allNotes[position].body)


    }

    override fun getItemCount(): Int {

        return allNotes.size
    }


 /*   @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<NoteEntity>) {

        allNotes.clear()

        allNotes.addAll(newList)

        notifyDataSetChanged()
    }*/
}*/

