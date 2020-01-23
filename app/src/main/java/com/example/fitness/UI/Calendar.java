package com.example.fitness.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.example.fitness.Custom.WorkoutDoneDecorator;
import com.example.fitness.Database.YogaDB;
import com.example.fitness.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;

public class Calendar extends AppCompatActivity {

    @BindView(R.id.calendar)
    MaterialCalendarView calendar;

    HashSet<CalendarDay> list = new HashSet<>();

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        yogaDB = new YogaDB(this);
//
//        List<String> workoutDay = new  YogaDB.getWorkoutDays();
//        HashSet<CalendarDay> convertedList = new HashSet<>();
//        for (String value:workoutDay)
//            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
//        calendar.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}
