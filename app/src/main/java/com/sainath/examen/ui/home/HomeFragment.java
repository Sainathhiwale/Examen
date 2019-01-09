package com.sainath.examen.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sainath.examen.R;

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
    LinearLayout bankcardId;

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

    @OnClick({R.id.technews_img,R.id.dq_imageView,R.id.interview_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.technews_img:
                Toast.makeText(getActivity(), "News is coming", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dq_imageView:
                Toast.makeText(getActivity(),"Daily quiz",Toast.LENGTH_SHORT).show();
                break;
            case R.id.interview_img:
                Toast.makeText(getActivity(),"Interview quiz",Toast.LENGTH_SHORT).show();
                break;
        }
    }
   /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/

   /* @OnClick (R.id.bankcardId)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.bankcardId:
                PythonQuizFragment pythonQuizFragment = new PythonQuizFragment();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,pythonQuizFragment);
                fragmentTransaction.commit();
        }
    }*/

}
