package com.example.ontime

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ontime.database.EventEntity
import kotlinx.android.synthetic.main.activity_update_event_screen.*

class UpdateEventScreen : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    val viewModel : NoteViewModel by viewModels()

    private var day=0
    private var month=0
    private var year=0
    private var hour=0
    private var minute=0

    private var savedDay=0
    private var savedMonth=0
    private var savedYear=0
    private var savedHour=0
    private var savedMinute=0

    private var SsavedDay=0
    private var SsavedMonth=0
    private var SsavedYear=0
    private var SsavedHour=0
    private var SsavedMinute=0

    private var FsavedDay=0
    private var FsavedMonth=0
    private var FsavedYear=0
    private var FsavedHour=0
    private var FsavedMinute=0

     var RsavedHour=0
     var RsavedMinute=0

     var repeatt=0

     var string=""

     var flag=-1

     var a=0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_event_screen)



        update_toolbar_new_event.setNavigationIcon(R.drawable.back_toolbar)
        setSupportActionBar(update_toolbar_new_event)
        pickDate()

        updateRepeat.setOnClickListener {
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v: View =inflater.inflate(R.layout.number_picker,null)
            val pic=v.findViewById<NumberPicker>(R.id.numberpicker)
            pic.minValue=0
            pic.maxValue=5
            val builder = AlertDialog.Builder(this,R.style.AlertDialog)

            builder.setView(v)
            builder.setTitle("Number of repeats")
            builder.setPositiveButton("Ok") { dialog, i ->
                repeatt=pic.value
                updateRepeat.text=pic.value.toString().plus(" >")
            }
            val alert=builder.create()
            alert.show()
            alert.window!!.setLayout(800,600)

        }

        a = intent.extras?.get("id") as Int
        val b = intent.extras?.get("title").toString()
        val c = intent.extras?.get("startFrom").toString()
        val e = intent.extras?.get("finish").toString()
        val g = intent.extras?.get("repeat").toString()
        val h = intent.extras?.get("reminder").toString()
        val i = intent.extras?.get("place").toString()
        val j = intent.extras?.get("notes").toString()

        updateTitleEvent.setText(b)
        var v=c.split('-')
        var da=v[0].split('/')
        SsavedDay=da[0].toInt()
        SsavedMonth=da[1].toInt()
        SsavedYear=da[2].toInt()
        var vv=v[1].split(':')
        SsavedHour=vv[0].toInt()
        SsavedMinute=vv[1].toInt()
        updateStartFrom.text = "${v[0]}-${Time(vv[0].toInt(),vv[1].toInt()).timeText} ${Time(vv[0].toInt(),vv[1].toInt()).ampmText}"
        v=e.split('-')
        da=v[0].split('/')
        FsavedDay=da[0].toInt()
        FsavedMonth=da[1].toInt()
        FsavedYear=da[2].toInt()
        vv=v[1].split(':')
        FsavedHour=vv[0].toInt()
        FsavedMinute=vv[1].toInt()
        updateFinish.text = "${v[0]}-${Time(vv[0].toInt(),vv[1].toInt()).timeText} ${Time(vv[0].toInt(),vv[1].toInt()).ampmText}"
        repeatt=g.toInt()
        updateRepeat.text = g
        vv=h.split(':')
        RsavedHour=vv[0].toInt()
        RsavedMinute=vv[1].toInt()
        updateReminder.text = "${Time(vv[0].toInt(),vv[1].toInt()).timeText} ${Time(vv[0].toInt(),vv[1].toInt()).ampmText}"
        updatePlaceEvent.setText(i)
        updateNoteEvent.setText(j)


    }
    private fun pickDate() {
        updateStartFrom.setOnClickListener{
            flag=0
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()

        }
        updateFinish.setOnClickListener{
            flag=1
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
        updateReminder.setOnClickListener{
            flag=2
            getDateTimeCalendar()
            TimePickerDialog(this,this,hour,minute,false).show()
        }
    }

    fun getDateTimeCalendar(){
        val calendar: Calendar = Calendar.getInstance()
        day=calendar.get(Calendar.DAY_OF_MONTH)
        month=calendar.get(Calendar.MONTH)
        year=calendar.get((Calendar.YEAR))
        hour=calendar.get(Calendar.HOUR)
        minute=calendar.get(Calendar.MINUTE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_schedule_event,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.check->{
                val titleEventI=updateTitleEvent.text.toString()
                val startFromI="$SsavedDay/$SsavedMonth/$SsavedYear-${Time(SsavedHour,SsavedMinute).makeDataForDB()}"
                val finishI="$FsavedDay/$FsavedMonth/$FsavedYear-${Time(FsavedHour,FsavedMinute).makeDataForDB()}"
                val repeatI=repeatt
                val reminderI=Time(RsavedHour,RsavedMinute).makeDataForDB()
                val placeI=updatePlaceEvent.text.toString()
                val notesI=updateNoteEvent.text.toString()
                val event= EventEntity(a,titleEventI,startFromI,finishI,repeatI,reminderI,placeI,notesI)
                viewModel.updateEvent(event)
                Toast.makeText(this,"done", Toast.LENGTH_SHORT).show()

            }
            R.id.checkbox->{

                Toast.makeText(this,"done", Toast.LENGTH_SHORT).show()
            }
            else->{
                return super.onOptionsItemSelected(item)
            }

        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        savedDay=day
        savedMonth=month
        savedYear=year
        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,false).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        savedMinute=minute
        savedHour=hour

         getDateTimeCalendar()




        if (flag==0) {

            SsavedDay=savedDay
            SsavedMonth=savedMonth
            SsavedYear=savedYear
            SsavedHour=savedHour
            SsavedMinute=savedMinute
            string="$SsavedDay/$SsavedMonth/$SsavedYear-${Time(SsavedHour,SsavedMinute).timeText} ${Time(SsavedHour,SsavedMinute).ampmText}"
            updateStartFrom.text = string

        }else if(flag==1){

            FsavedDay=savedDay
            FsavedMonth=savedMonth
            FsavedYear=savedYear
            FsavedHour=savedHour
            FsavedMinute=savedMinute
            string="$FsavedDay/$FsavedMonth/$FsavedYear-${Time(FsavedHour,FsavedMinute).timeText} ${Time(FsavedHour,FsavedMinute).ampmText}"
            updateFinish.text = string
        }else if (flag==2){
            RsavedHour=savedHour
            RsavedMinute=savedMinute
            string="${Time(RsavedHour,RsavedMinute).timeText} ${Time(RsavedHour,RsavedMinute).ampmText}"
            updateReminder.text=string

        }

    }


}

























