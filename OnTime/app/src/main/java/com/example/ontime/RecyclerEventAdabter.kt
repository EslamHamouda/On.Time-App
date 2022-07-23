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
import com.example.ontime.database.EventEntity
import com.example.ontime.database.NoteEntity
import java.sql.Date
import java.sql.Time
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import kotlin.collections.ArrayList


class RecyclerEventAdabter(val context: Context,
                          var EventList: ArrayList<EventEntity>) :RecyclerView.Adapter<RecyclerEventAdabter.EventViewHolder>() {

    class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleEvent)
        val date: TextView = itemView.findViewById(R.id.dateEvent)
        val time: TextView = itemView.findViewById(R.id.timeEvent)
        val place: TextView = itemView.findViewById(R.id.placeEventR)
        val notes: TextView = itemView.findViewById(R.id.notesEventR)
        val linear:LinearLayout=itemView.findViewById(R.id.linearEvent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.event_item,
            parent, false
        )
        return EventViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var tit: String? =EventList[position].title
        var pla: String? =EventList[position].place
        var not: String? =EventList[position].notes
        holder.title.text = tit.toString()

        val v=EventList[position].startFrom.split('-')
        val d=v[0].split('/')
        val vv=v[1].split(':')
        holder.date.text=d.toString()
        holder.time.text="${Time(vv[0].toInt(),vv[1].toInt()).timeText} ${Time(vv[0].toInt(),vv[1].toInt()).ampmText}"
        holder.place.text=pla.toString()
        holder.notes.text=not.toString()




      holder.linear.setOnClickListener{
            val intent=Intent(context,UpdateEventScreen::class.java)

            intent.putExtra("id",EventList[position].id)
            intent.putExtra("title",EventList[position].title)
            intent.putExtra("startFrom",EventList[position].startFrom)
            intent.putExtra("finish",EventList[position].finish)
            intent.putExtra("repeat",EventList[position].repeat)
            intent.putExtra("reminder",EventList[position].reminder)
            intent.putExtra("place",EventList[position].place)
            intent.putExtra("notes",EventList[position].notes)


            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return EventList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilteredList:ArrayList<EventEntity>){
        EventList=newFilteredList
        notifyDataSetChanged()
    }
}
