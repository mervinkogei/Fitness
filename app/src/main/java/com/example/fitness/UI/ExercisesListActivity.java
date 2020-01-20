package com.example.fitness.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.fitness.Adapter.RecyclerViewAdapter;
import com.example.fitness.Models.Exercise;
import com.example.fitness.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisesListActivity extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        initData();

        recyclerView =(RecyclerView)findViewById(R.id.recycler_exercises);
        adapter = new RecyclerViewAdapter(this,exerciseList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
