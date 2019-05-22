package com.sainath.examen.ui.interview.experienced;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sainath.examen.R;
import com.sainath.examen.adapter.ExperienceAdapter;
import com.sainath.examen.data.model.expreience.Experience;
import com.sainath.examen.data.model.user.User;
import com.sainath.examen.data.room_database.entity.AddQuiz;
import com.sainath.examen.data.view_model.AddQuizViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExperienceFragment extends Fragment {
    private static final String TAG = "ExperienceFragment";
   private List<Experience> list = new ArrayList<>();
   private ExperienceAdapter adapter;
   private RecyclerView recyclerView;
   private AddQuizViewModel addQuizViewModel;


      private DatabaseReference mDatabase;
      private DatabaseReference mMessageReference;
      private DataSnapshot snapshot;

    public ExperienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvExperience);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        addQuizViewModel = ViewModelProviders.of(this).get(AddQuizViewModel.class);
        addQuizViewModel.getAllQuizDetails().observe(this, new Observer<List<AddQuiz>>() {
            @Override
            public void onChanged(@Nullable List<AddQuiz> addQuizs) {
            adapter.setAddQuizChanges(addQuizs);
            }
        });

      //  initData();

        return view;

    }




    private void initData() {
        Experience experience = new Experience("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experience);
        Experience experience2 = new Experience("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experience2);
        Experience experience3 = new Experience("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experience3);
        Experience experience4 = new Experience("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experience4);
    }

}
