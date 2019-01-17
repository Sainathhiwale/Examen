package com.sainath.examen.ui.interview.fresher;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainath.examen.R;
import com.sainath.examen.adapter.FresherAdapter;
import com.sainath.examen.data.model.fresher.FresherQuiz;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FresherFragment extends Fragment {
   private List<FresherQuiz> fresherQuizList;
   private FresherAdapter fresherAdapter;
   private RecyclerView recyclerView;
    public FresherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fresher, container, false);
        recyclerView  =(RecyclerView)view.findViewById(R.id.rvFresher);
        fresherQuizList = new ArrayList<>();
        fresherAdapter = new FresherAdapter(fresherQuizList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fresherAdapter);
        initData();
        return view;
    }

    private void initData() {
        FresherQuiz experienceQuiz = new FresherQuiz("What is Android","Android is os it's platform to consist middle layer of some key app");
        fresherQuizList.add(experienceQuiz);
    }

}
