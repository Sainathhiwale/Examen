package com.sainath.examen.ui.interview.fresher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainath.examen.R;
import com.sainath.examen.adapter.FresherAdapter;
import com.sainath.examen.data.model.fresher.Fresher;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FresherFragment extends Fragment {
   private List<Fresher> fresherList = new ArrayList<>();
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

        fresherAdapter = new FresherAdapter(fresherList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fresherAdapter);
        initData();
        return view;
    }

    private void initData() {
        Fresher e1 = new Fresher("1.Tell me about Yourself?","My name is sainath hiwale I belongsto Aurangabad and I have done MCA in Computer science.I have four members in my family at Aurangabad");
        fresherList.add(e1);
        Fresher e2 = new Fresher("2.What is your greatest weakness?","I can not say no when someone ask for help and i am a bit lazy about which i am not interested ");
        fresherList.add(e2);
        Fresher e3 = new Fresher("3.How do you handle stress and pressure?","stress is very important to me because I do best under stress and pressure. I’ve found that I really enjoy working in a challenging environment.");
        fresherList.add(e3);
        Fresher e4 = new Fresher("4.Why should we hire you?","Sir,as I am a fresher,i have theoretical knowledge but I can do hard work for my organization.And I will put all my efforts\n" +
                "for the good progress of organization.Being punctual ans sincere, I can finish the work given to me on time and try\n" +
                "my best to fulfill and the needs of company from me.");
        fresherList.add(e4);
        Fresher e5 = new Fresher("5.Why do you want to work here?","Sir, it is a great privilege for anyone to work in a reputed company like yours.\n" +
                "        When I read about your company I found that my skills are matching your requirements.\n" +
                "        Here I can showcase my skills to contribute to the company growth.");
                fresherList.add(e5);







    }

}
