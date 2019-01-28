package com.sainath.examen.ui.tutorial.java_tut;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainath.examen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JavaTutFragment extends Fragment {


    public JavaTutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_java_tut, container, false);
    }

}
