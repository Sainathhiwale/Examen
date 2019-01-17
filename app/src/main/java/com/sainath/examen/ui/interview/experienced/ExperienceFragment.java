package com.sainath.examen.ui.interview.experienced;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainath.examen.R;
import com.sainath.examen.adapter.ExperienceAdapter;
import com.sainath.examen.data.model.expreience.ExperienceQuiz;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExperienceFragment extends Fragment {
   private List<ExperienceQuiz> list = new ArrayList<>();
   private ExperienceAdapter adapter;
   private RecyclerView recyclerView;



    public ExperienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvExperience);

        adapter = new ExperienceAdapter (list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        initData();
        return view;
    }

    private void initData() {
        ExperienceQuiz experienceQuiz = new ExperienceQuiz("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experienceQuiz);
        ExperienceQuiz experienceQuiz2 = new ExperienceQuiz("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experienceQuiz2);
        ExperienceQuiz experienceQuiz3 = new ExperienceQuiz("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experienceQuiz3);
        ExperienceQuiz experienceQuiz4 = new ExperienceQuiz("What is Android","Android is os it's platform to consist middle layer of some key app");
        list.add(experienceQuiz4);
    }

}
