package com.sainath.examen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainath.examen.R;
import com.sainath.examen.data.room_database.entity.AddQuiz;
import com.sainath.examen.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {
    private List<AddQuiz> addQuizList = new ArrayList<>();
    private ExperienceAdapter.OnItemClickListener onItemClickListener;

    public ExperienceAdapter(List<AddQuiz> addQuizs) {
        this.addQuizList = addQuizs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AddQuiz addQuiz = addQuizList.get(position);
        if (addQuiz != null) {
            holder.tvpriority.setText(String.valueOf(addQuiz.getPriority()));
        } else {
            holder.tvpriority.setText(AppConstants.EMPTY);
        }
        if (addQuiz != null) {
            holder.tvquiz.setText(addQuiz.getQuiz());
        } else {
            holder.tvquiz.setText(AppConstants.EMPTY);
        }
        if (addQuiz != null) {
            holder.tvAns.setText(addQuiz.getAnswer());

        }else {
            holder.tvAns.setText(AppConstants.EMPTY);
        }
    }


    @Override
    public int getItemCount() {
        return addQuizList.size();
    }

    public void setAddQuizChanges(List<AddQuiz> addQuizs) {
        this.addQuizList = addQuizs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tvquiz)
        TextView tvquiz;
        @Bind(R.id.tvAns)
        TextView tvAns;
        @Bind(R.id.tvpriority)
        TextView tvpriority;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(itemView, (AddQuiz) itemView.getTag());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, AddQuiz addQuiz);
    }
}
