package com.example.fitness.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.fitness.AlarmNotifictionReceiver;
import com.example.fitness.Database.YogaDB;
import com.example.fitness.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.rdiGroup)
    RadioGroup rdiGroup;
    @BindView(R.id.rdiEasy)
    RadioButton rdiEasy;
    @BindView(R.id.rdiMedium)
    RadioButton rdiMedium;
    @BindView(R.id.rdiHard)
    RadioButton rdiHard;
    @BindView(R.id.switchAlarm)
    ToggleButton switchAlarm;
    @BindView(R.id.time_picker)
    TimePicker time_picker;

    YogaDB yogaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        yogaDB = new YogaDB(this);

//        int mode = yogaDB.getSettingMode();
//        setRadioButton(mode);

        //Event
     btn_save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             saveWorkOutMode();
               saveAlarm(switchAlarm.isChecked());
             Toast.makeText(SettingsActivity.this,"Your have Successfully Saved",Toast.LENGTH_SHORT).show();
             finish();
         }
    });
    }

    private void saveAlarm(boolean checked) {
        if (checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingsActivity.this, AlarmNotifictionReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            //Set Time for the Alarm
            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
//            calendar.set(toDay.getYear(),toDay.getMonth(),toDay.getDay(),time_picker.getHour(),time_picker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        }else {
            //Cancel Alarm
            Intent intent = new Intent(SettingsActivity.this, AlarmNotifictionReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);

        }
    }

    private void saveWorkOutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if (selectedID == rdiEasy.getId())
            yogaDB.saveSettingMode(0);
        else if (selectedID == rdiMedium.getId())
            yogaDB.saveSettingMode(1);
        else if (selectedID == rdiHard.getId())
            yogaDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if (mode == 0)
            rdiGroup.check(R.id.rdiEasy);
        else if (mode == 1)
            rdiGroup.check(R.id.rdiMedium);
        else if (mode == 2)
            rdiGroup.check(R.id.rdiHard);
    }
}
