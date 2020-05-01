package com.tanzible.teacherfinder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tanzible.teacherfinder.R;

public class DashboardActivity extends AppCompatActivity {

    private CardView primaryView;
    private CardView secondaryView;
    private CardView higherView;
    private CardView graduationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        primaryView = findViewById(R.id.primary);
        secondaryView = findViewById(R.id.secondary);
        higherView = findViewById(R.id.higher);
        graduationView = findViewById(R.id.graduation);



        primaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryActivity.class));
            }
        });

        secondaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SecondaryActivity.class));
            }
        });

        higherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HigherActivity.class));
            }
        });

        graduationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GraduationAcitvity.class));
            }
        });




    }
}
