package com.example.ontime.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ontime.*
import com.example.ontime.database.EventEntity
import com.example.ontime.database.NoteEntity
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : Fragment() {

    val viewModel : NoteViewModel by viewModels()
    lateinit var adabter: RecyclerEventAdabter
    lateinit var copyOfOldList: List<EventEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getEvents().observe(viewLifecycleOwner) { eventList ->
            recycler_view_event.layoutManager =
                LinearLayoutManager(context)

            copyOfOldList = eventList
            adabter = RecyclerEventAdabter(requireContext(), eventList as ArrayList<EventEntity>)

            recycler_view_event.adapter = adabter

            val swipe = ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(recycler_view_event)

            calendarViewSchedule.setOnDateChangeListener { view, year, month, day ->
                val intent=Intent(context,NewScheduleScreen::class.java)
                intent.putExtra("key","calDate")
                intent.putExtra("year",year)
                intent.putExtra("month",month)
                intent.putExtra("day",day)
                startActivity(intent)

            }
        }



        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
    private fun initSwipeToDelete(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                copyOfOldList[position].id?.let { viewModel.deleteEvent(it) }
                Toast.makeText(activity, "Event Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}