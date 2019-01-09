package com.sainath.examen.ui.android_tut;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainath.examen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidTutFragment extends Fragment {


    public AndroidTutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_tut, container, false);
    }

}
