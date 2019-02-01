package com.sainath.examen.ui.quiz.android_quiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sainath.examen.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidQuizActivity extends AppCompatActivity {
    @Bind(R.id.llAndyBegin)
    LinearLayout llAndyBegin;
    @Bind(R.id.llAndyInter)
    LinearLayout llAndyInter;
    @Bind(R.id.llAndyAdv)
    LinearLayout llAndyAdv;
    @Bind(R.id.tvBackArrow)
    TextView tvBackArrow;
    @Bind(R.id.llSampleCode)
    LinearLayout llSampleCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_quiz);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.llAndyBegin,R.id.llAndyInter,R.id.llAndyAdv,R.id.tvBackArrow})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.llAndyBegin:
                Toast.makeText(this, "Android beginners Coming soom", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llAndyInter:
                Toast.makeText(this, "Android intermediate comming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llAndyAdv:
                Toast.makeText(this, "Android Advanced is comming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvBackArrow:
                finish();
                break;
            case R.id.llSampleCode:
                String url ="https://github.com/Sainathhiwale/android-logical-code";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

        }
    }

}
