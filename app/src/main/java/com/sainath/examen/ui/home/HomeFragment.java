package com.sainath.examen.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sainath.examen.R;
import com.sainath.examen.ui.interview.InterviewActivity;
import com.sainath.examen.ui.news.NewsActivity;
import com.sainath.examen.ui.quiz.android_quiz.AndroidQuizActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.technews_img)
    ImageView technews_img;
    @Bind(R.id.dq_imageView)
    ImageView dq_imageView;
    @Bind(R.id.interview_img)
    ImageView interview_img;
    @Bind(R.id.llinerAndroid)
    LinearLayout llinerAndroid;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.technews_img,R.id.dq_imageView,R.id.interview_img,R.id.llinerAndroid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.technews_img:
                Intent newsIntent = new Intent(getActivity(), NewsActivity.class);
                startActivity(newsIntent);
                break;
            case R.id.dq_imageView:
                Toast.makeText(getActivity(),"Daily quiz",Toast.LENGTH_SHORT).show();
                break;
            case R.id.interview_img:
                Intent interviewIntent = new Intent(getActivity(), InterviewActivity.class);
                startActivity(interviewIntent);
                break;
            case R.id.llinerAndroid:
               Intent intentAndroid = new Intent(getActivity(), AndroidQuizActivity.class);
               startActivity(intentAndroid);
        }
    }


}
