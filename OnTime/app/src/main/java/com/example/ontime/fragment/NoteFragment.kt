package com.example.ontime.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ontime.NoteViewModel
import com.example.ontime.R
import com.example.ontime.RecyclerNoteAdabter
import com.example.ontime.database.NoteEntity
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment() {

    val viewModel : NoteViewModel by viewModels()
    lateinit var adabter:RecyclerNoteAdabter
    lateinit var copyOfOldList: List<NoteEntity>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            recycler_view.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

            copyOfOldList=notesList
            adabter= RecyclerNoteAdabter(requireContext(), notesList as ArrayList<NoteEntity>)

            recycler_view.adapter = adabter

            val swipe = ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(recycler_view)

            searchView.queryHint="Search Note Here"
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })
        }


         return inflater.inflate(R.layout.fragment_note, container, false)
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
                copyOfOldList[position].id?.let { viewModel.deleteNotes(it) }
                Toast.makeText(activity, "Note Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun NotesFiltering(searchText: String?) {

        val newFilteredList = arrayListOf<NoteEntity>()
        for(i in copyOfOldList){
            if(i.title.lowercase().contains(searchText!!.lowercase()) || i.body.lowercase().contains(searchText!!.lowercase())){
                newFilteredList.add(i)
            }
        }
        adabter.filtering(newFilteredList)
    }

}