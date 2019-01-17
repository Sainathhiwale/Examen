package com.sainath.examen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainath.examen.R;
import com.sainath.examen.data.model.expreience.ExperienceQuiz;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>  {
    private List<ExperienceQuiz> experienceQuizList;

    public ExperienceAdapter(List<ExperienceQuiz> experienceQuizList) {
        this.experienceQuizList = experienceQuizList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvQuestion.setText(experienceQuizList.get(position).getAnwser());
        holder.tvAnswer.setText(experienceQuizList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return experienceQuizList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView tvQuestion,tvAnswer;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvQuestion = (TextView)itemView.findViewById(R.id.tvQuestion);
            tvAnswer  = (TextView)itemView.findViewById(R.id.tvAnswer);
        }
    }
}
