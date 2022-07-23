package com.example.ontime

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ontime.fragment.FragmentPagerAdapter
import com.example.ontime.fragment.NoteFragment
import com.example.ontime.fragment.ScheduleFragment
import kotlinx.android.synthetic.main.activity_schedule_page.*


class SchedulePage : AppCompatActivity() {

    private val rotateOpen:Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_animation_open)}
    private val rotateClose:Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_animation_close)}
    private val fBottom:Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.from_bottom)}
    private val tBottom:Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.to_bottom)}
    var clicked=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_page)
        setSupportActionBar(schedule_page_toolbar)
        setUpTabs()
        fab.setOnClickListener{
            fabButtons()
        }
        fabEvent.setOnClickListener{
            startActivity(Intent(this,NewScheduleScreen::class.java))
        }
        fabNote.setOnClickListener{
            startActivity(Intent(this,NewNoteScreen::class.java))
        }

    }

    private fun fabButtons() {
      setVisiblity(clicked)
        setAnimation(clicked)
      clicked=!clicked
    }

    private fun setAnimation(clicked: Boolean) {
           if (!clicked){
               fab.startAnimation(rotateOpen)
               fabEvent.startAnimation(fBottom)
               fabNote.startAnimation(fBottom)
           }else{
               fab.startAnimation(rotateClose)
               fabEvent.startAnimation(tBottom)
               fabNote.startAnimation(tBottom)
           }
    }

    private fun setVisiblity(clicked: Boolean) {
        if (!clicked){
            fabEvent.visibility=View.VISIBLE
            fabNote.visibility=View.VISIBLE
        }else{
            fabEvent.visibility=View.INVISIBLE
            fabNote.visibility=View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_schedule_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.notification_history_icon->{
             val intent=Intent(this,NotificationHistory::class.java)
                startActivity(intent)
            }
            R.id.setting->{

            }
            else->{
                return super.onOptionsItemSelected(item)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpTabs() {
       val adapter=FragmentPagerAdapter(supportFragmentManager)
        adapter.addFragment(ScheduleFragment(),"Schedule")
        adapter.addFragment(NoteFragment(),"Note")

        view_pager.adapter=adapter
        tabs.setupWithViewPager(view_pager)
    }

}