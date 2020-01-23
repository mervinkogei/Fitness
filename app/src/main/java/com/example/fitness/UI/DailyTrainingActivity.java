package com.example.fitness.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitness.Database.YogaDB;
import com.example.fitness.Models.Exercise;
import com.example.fitness.R;
import com.example.fitness.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyTrainingActivity extends AppCompatActivity {
    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.btn_start)
    Button btn_start;
    @BindView(R.id.txt_count_down) TextView txt_count_down;
    @BindView(R.id.txt_get_ready) TextView txt_get_ready;
    @BindView(R.id.view_exercises) TextView view_exercises;
    @BindView(R.id.detail_image)
    ImageView detail_image;
    @BindView(R.id.layout_get_ready)
    LinearLayout layout_get_ready;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<Exercise> exerciseList = new ArrayList<>();

    int ex_id = 0,time_limit=0;

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);
        ButterKnife.bind(this);

        initData();

//        yogaDB = new YogaDB(this);
//        if (yogaDB.getSettingMode() ==0)
//            time_limit = Common.TIME_LIMIT_EASY;
//        else if (yogaDB.getSettingMode() ==1)
//            time_limit = Common.TIME_LIMIT_MEDIUM;
//        else if (yogaDB.getSettingMode() ==2)
//            time_limit = Common.TIME_LIMIT_HARD;

        progressBar.setMax(exerciseList.size());

        setExerciseInformation(ex_id);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_start.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    btn_start.setText("Done");
                }
                else if (btn_start.getText().toString().toLowerCase().equals("done")){

                    exerciseCountDown.cancel();
                    restTimeCountDown.cancel();

                    if (ex_id <exerciseList.size()-1){
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        timer.setText("");
                    }

                }else{
                    showFinished();
                }
            }
        });
    }

    private void showRestTime() {
        detail_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        btn_start.setText("Skip");
        timer.setVisibility(View.VISIBLE);
        layout_get_ready.setVisibility(View.VISIBLE);

        restTimeCountDown.start();

        txt_get_ready.setText("REST TIME");
    }

    private void showGetReady() {
        detail_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);

        layout_get_ready.setVisibility(View.VISIBLE);

        txt_get_ready.setText("GET READY");
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long l) {
                txt_count_down.setText(""+(1-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if (ex_id < exerciseList.size()-1){
            detail_image.setVisibility(View.VISIBLE);
            btn_start.setVisibility(View.VISIBLE);
            layout_get_ready.setVisibility(View.INVISIBLE);

            exerciseCountDown.start();

            // set Data
            detail_image.setImageResource(exerciseList.get(ex_id).getImage_id());
            view_exercises.setText(exerciseList.get(ex_id).getName());

        }else {
            showFinished();
        }
    }

    private void showFinished() {
        detail_image.setVisibility(View.INVISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layout_get_ready.setVisibility(View.VISIBLE);

        txt_get_ready.setText("FINISHED");
        txt_count_down.setText("Congratulations ! \n You're done with Today's Exercise");

        //Save Workout
        yogaDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
    }

    // Counter Down
    CountDownTimer exerciseCountDown = new CountDownTimer(time_limit,1000) {
        @Override
        public void onTick(long l) {
            timer.setText(""+1/1000);
        }

        @Override
        public void onFinish() {
            if (ex_id < exerciseList.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                timer.setText("");

                setExerciseInformation(ex_id);
                btn_start.setText("Start");
            }else{
                showFinished();
            }
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {
            timer.setText(""+1/1000);
        }

        @Override
        public void onFinish() {
          setExerciseInformation(ex_id);
          showExercises();
        }
    };

    private void setExerciseInformation(int id) {
        detail_image.setImageResource(exerciseList.get(id).getImage_id());
        view_exercises.setText(exerciseList.get(id).getName());
        btn_start.setText("Start");

        detail_image.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);

        layout_get_ready.setVisibility(View.INVISIBLE);

    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.yoga_arm,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_back,"Yoga Back"));
        exerciseList.add(new Exercise(R.drawable.yoga_back_pain,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_chair,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_east_side,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_fit,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_rest,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_side_strech,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_tune,"Yoga Arm"));
        exerciseList.add(new Exercise(R.drawable.yoga_waist,"Yoga Arm"));


    }
}
