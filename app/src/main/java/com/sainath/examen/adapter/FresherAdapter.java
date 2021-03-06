package com.sainath.examen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainath.examen.R;
import com.sainath.examen.data.model.fresher.Fresher;

import java.util.List;

public class FresherAdapter extends RecyclerView.Adapter<FresherAdapter.MyViewHolder>  {
    private List<Fresher> fresherList;

    public FresherAdapter(List<Fresher> fresherList) {
        this.fresherList = fresherList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fresher_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvFQuestion.setText(fresherList.get(position).getQuestion());
        holder.tvFAnswer.setText(fresherList.get(position).getAnwser());
    }

    @Override
    public int getItemCount() {
        return fresherList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFQuestion,tvFAnswer;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvFQuestion = (TextView)itemView.findViewById(R.id.tvFQuestion);
            tvFAnswer = (TextView)itemView.findViewById(R.id.tvFAnswer);
        }
    }
}
