package com.sainath.examen.ui.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sainath.examen.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends AppCompatActivity {
    @Bind(R.id.llTechNews)
    LinearLayout llTechNews;
    @Bind(R.id.llHackerNS)
    LinearLayout llHackerNS;
    @Bind(R.id.llNews)
    LinearLayout llNews;
    @Bind(R.id.tvBKANews)
    TextView tvBKANews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llTechNews,R.id.llHackerNS,R.id.llNews,R.id.tvBKANews})
    public void onViewCliked(View view){
        int id = view.getId();
       if (id == R.id.llTechNews){
           Toast.makeText(this, "Tech News is Coming soon...", Toast.LENGTH_SHORT).show();
       }else if (id == R.id.llHackerNS){
           Toast.makeText(this, "Hacker news is coming soon", Toast.LENGTH_SHORT).show();
       }else if (id == R.id.llNews){
           Toast.makeText(this, "Only Normal News is coming soon..", Toast.LENGTH_SHORT).show();
       }else if (id ==R.id.tvBKANews){
           finish();
       }
    }

}
