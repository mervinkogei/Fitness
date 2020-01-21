package com.example.fitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.Interface.ItemClickListener;
import com.example.fitness.Models.Exercise;
import com.example.fitness.R;
import com.example.fitness.UI.ViewExercisesActivity;

import java.util.List;
import java.util.zip.Inflater;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        image = (ImageView)itemView.findViewById(R.id.ex_image);
        text= (TextView)itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClick(view,getAdapterPosition());

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private Context context;
    private List<Exercise> exerciseList;

    public RecyclerViewAdapter(Context context, List<Exercise> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                //Call the Activity
//                Toast.makeText(context,"Click to " + exerciseList.get(position).getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ViewExercisesActivity.class);
                intent.putExtra("image_id",exerciseList.get(position).getImage_id());
                intent.putExtra("name",exerciseList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
