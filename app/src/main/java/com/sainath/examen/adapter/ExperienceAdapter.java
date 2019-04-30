package com.sainath.examen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainath.examen.R;
import com.sainath.examen.data.model.expreience.Experience;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>  {
    private List<Experience> experienceList;

    public ExperienceAdapter(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvQuestion.setText(experienceList.get(position).getAnwser());
        holder.tvAnswer.setText(experienceList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
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
