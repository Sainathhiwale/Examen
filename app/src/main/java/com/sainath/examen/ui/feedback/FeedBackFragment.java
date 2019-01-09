package com.sainath.examen.ui.feedback;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sainath.examen.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedBackFragment extends Fragment {
   @Bind(R.id.etFeedBack)
    EditText etFeedBack;
   @Bind(R.id.btnFeedBack)
    Button btnFeedBack;

    public FeedBackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnFeedBack)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btnFeedBack:
                sendToCustomer();
                break;
        }
    }

    private void sendToCustomer() {
        String feedback = etFeedBack.getText().toString().trim();
        Toast.makeText(getActivity(),feedback,Toast.LENGTH_SHORT).show();
    }

}
