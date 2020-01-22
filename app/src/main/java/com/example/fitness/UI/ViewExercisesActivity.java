package com.example.fitness.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitness.Database.YogaDB;
import com.example.fitness.R;
import com.example.fitness.Utils.Common;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewExercisesActivity extends AppCompatActivity {
    int image_id;
    String name;

    @BindView(R.id.view_exercises)
    TextView view_exercises;
    @BindView(R.id.timer) TextView timer;
    @BindView(R.id.btn_start)
    Button btn_start;
    @BindView(R.id.detail_image)
    ImageView detail_image;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);
        ButterKnife.bind(this);


//        yogaDB = new YogaDB(this);

//        timer.setText(" ");

        boolean isRunning = false;

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    btn_start.setText("DONE");

//                    int timeLimit = 0;
//                    if (yogaDB.getSettingMode() == 0)
//                        timeLimit = Common.TIME_LIMIT_EASY;
//                    else if (yogaDB.getSettingMode() == 1)
//                        timeLimit = Common.TIME_LIMIT_MEDIUM;
//                    else if (yogaDB.getSettingMode() == 2)
//                        timeLimit = Common.TIME_LIMIT_HARD;

                    new CountDownTimer(20000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText("" + 1 / 1000);
                        }

                        @Override
                        public void onFinish() {
                            // Add ads Here
                            Toast.makeText(ViewExercisesActivity.this, "Finish !!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                } else {
                    Toast.makeText(ViewExercisesActivity.this, "Finish !!", Toast.LENGTH_SHORT).show();
                    finish();
                }
//                isRunning = !isRunning;
            }
        });
        timer.setText(" ");

        if (getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name= getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            view_exercises.setText(name);
        }

    }
}
