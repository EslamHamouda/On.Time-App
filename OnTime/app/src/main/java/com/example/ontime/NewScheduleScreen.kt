package com.example.ontime

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import com.example.ontime.database.EventEntity
import com.example.ontime.database.NoteEntity
import kotlinx.android.synthetic.main.activity_new_schedule_screen.*
import kotlinx.android.synthetic.main.activity_update_note_screen.*
import java.sql.Date
import java.util.*


class NewScheduleScreen : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    val viewModel : NoteViewModel by viewModels()

    var day=0
    var month=0
    var year=0
    var hour=0
    var minute=0

    var savedDay=0
    var savedMonth=0
    var savedYear=0
    var savedHour=0
    var savedMinute=0
    var savedAmPm=""

    var SsavedDay=0
    var SsavedMonth=0
    var SsavedYear=0
    var SsavedHour=0
    var SsavedMinute=0


    var FsavedDay=0
    var FsavedMonth=0
    var FsavedYear=0
    var FsavedHour=0
    var FsavedMinute=0


    var RsavedHour=0
    var RsavedMinute=0
    var repeatt=0

    var string=""

    var flag=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule_screen)
        toolbar_new_event.setNavigationIcon(R.drawable.back_toolbar)
        setSupportActionBar(toolbar_new_event)

        pickDate()

        alertDialog()

        if (intent.extras?.get("key") =="calDate"){
            var b = intent.extras?.get("year")
            var c = intent.extras?.get("month")
            var e = intent.extras?.get("day")
            getDateTimeCalendar()
            TimePickerDialog(this,this,hour,minute,false).show()
            SsavedDay=e as Int
            SsavedMonth=c as Int
            SsavedYear=b as Int
            savedHour=hour
            savedMinute=minute
            SsavedHour=savedHour
            SsavedMinute=savedMinute

            val st="$SsavedDay/$SsavedMonth/$SsavedYear-${Time(savedHour,savedMinute).timeText} ${Time(savedHour,savedMinute).ampmText}"
            startFrom.text=st

        }



    }

    private fun alertDialog() {
        repeat.setOnClickListener {
            val inflater:LayoutInflater= getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v:View=inflater.inflate(R.layout.number_picker,null)
            val pic=v.findViewById<NumberPicker>(R.id.numberpicker)
            pic.minValue=0
            pic.maxValue=5
            val builder = AlertDialog.Builder(this,R.style.AlertDialog)

            builder.setView(v)
            builder.setTitle("Number of repeats")
            builder.setPositiveButton("Ok") { dialog, i ->
                repeatt=pic.value
                repeat.text=pic.value.toString()
            }
            val alert=builder.create()
            alert.show()
            alert.window!!.setLayout(800,600)

        }
    }

    private fun pickDate() {
        startFrom.setOnClickListener{
            flag=0
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()

        }
        finish.setOnClickListener{
            flag=1
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
        reminder.setOnClickListener{
            flag=2
            getDateTimeCalendar()
            TimePickerDialog(this,this,hour,minute,false).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_schedule_event,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.check->{
                val titleEventI=TitleEvent.text.toString()

                val startFromI= "$SsavedDay/$SsavedMonth/$SsavedYear-${Time(SsavedHour,SsavedMinute).makeDataForDB()}"

                var finishI="$FsavedDay/$FsavedMonth/$FsavedYear-${Time(FsavedHour,FsavedMinute).makeDataForDB()}"
                var repeatI=repeatt

                var reminderI= Time(RsavedHour,RsavedMinute).makeDataForDB()
                var placeI=placeEvent.text.toString()
                var notesI=noteEvent.text.toString()
                var event=EventEntity(null,titleEventI,startFromI,finishI,repeatI,reminderI,placeI,notesI)
               viewModel.addEvent(event)
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

    fun getDateTimeCalendar(){
        val calendar:Calendar=Calendar.getInstance()
        day=calendar.get(Calendar.DAY_OF_MONTH)
        month=calendar.get(Calendar.MONTH)
        year=calendar.get((Calendar.YEAR))
        hour=calendar.get(Calendar.HOUR)
        minute=calendar.get(Calendar.MINUTE)
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

            string="$savedDay/$savedMonth/$savedYear-${Time(savedHour,savedMinute).timeText} ${Time(savedHour,savedMinute).ampmText}"

        if (flag==0) {

            startFrom.text = string
            SsavedDay=savedDay
            SsavedMonth=savedMonth
            SsavedYear=savedYear
            SsavedHour=savedHour
            SsavedMinute=savedMinute

        }else if(flag==1){

            finish.text = string
            FsavedDay=savedDay
            FsavedMonth=savedMonth
            FsavedYear=savedYear
            FsavedHour=savedHour
            FsavedMinute=savedMinute

        }else if (flag==2){

            string="${Time(savedHour,savedMinute).timeText} ${Time(savedHour,savedMinute).ampmText}"
            reminder.text=string
            RsavedHour=savedHour
            RsavedMinute=savedMinute

        }

    }

    fun amPm(savedHour:Int){
        if (savedHour==0){
            this.savedHour+=12
            savedAmPm+="AM"
        }else if (savedHour==12){
            savedAmPm+="PM"
        }else if (savedHour>12){
            this.savedHour-=12
            savedAmPm="PM"
        }else{
            savedAmPm="AM"
        }
    }
}
