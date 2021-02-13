package com.abramovae.newproject.view


import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.abramovae.newproject.R
import java.util.*


class FragmentCalendar: Fragment(), CalendarView.OnDateChangeListener{

    private var hasPermissions = false
    private var hasRequestPermissions = false

    companion object {
        fun newInstance(): FragmentCalendar {
            val args = Bundle()
            val fragment = FragmentCalendar()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        var view = inflater.inflate(R.layout.fragment_calendar, container, false)
        var calendarView: CalendarView = view.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener(this)
        checkPermissions()
        return view
    }

    override fun onSelectedDayChange(v: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            Log.d("TAG", "add to calendar")
            if (hasPermissions) {
                addEvent(year, month, dayOfMonth)
                this.fragmentManager?.popBackStack()
            } else{
                Log.d("TAG", "request permissions")
                var array = arrayOf(android.Manifest.permission.READ_CALENDAR, android.Manifest.permission.WRITE_CALENDAR)
                requestPermissions(array, 100)

            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        hasRequestPermissions = true
        checkPermissions()
    }




    private fun addEvent(year: Int, month: Int, dayOfMonth: Int){
            Log.d("TAG", "has permissions")
            val cr: ContentResolver = activity!!.getContentResolver()
            val values = ContentValues()
            var date = Calendar.getInstance()
            date.set(year, month, dayOfMonth)
            values.put(CalendarContract.Events.DTSTART, date.timeInMillis)
            values.put(CalendarContract.Events.TITLE, "title")
            values.put(CalendarContract.Events.DESCRIPTION, "comment")
            val timeZone = TimeZone.getDefault()
            values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.id)
            values.put(CalendarContract.Events.DURATION, "+P1H")
            values.put(CalendarContract.Events.CALENDAR_ID, 1)
            val uri = cr.insert(CalendarContract.Events.CONTENT_URI, values)
            Log.d("TAG", uri.toString())
            Toast.makeText(activity, "event was successfully added", Toast.LENGTH_SHORT).show()

    }


    private fun checkPermissions(){
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED){
            hasPermissions = true
        }

    }
}