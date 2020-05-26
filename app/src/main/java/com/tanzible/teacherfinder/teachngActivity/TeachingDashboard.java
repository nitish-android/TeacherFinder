package com.tanzible.teacherfinder.teachngActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.LearningMyAccount;
import com.tanzible.teacherfinder.teachngActivity.masterGraduation.Master_GraduationActivity;
import com.tanzible.teacherfinder.teachngActivity.masterHigher.Master_HigherActivity;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryActivity;
import com.tanzible.teacherfinder.teachngActivity.masterSecondry.Master_SecondryActivity;

public class TeachingDashboard extends AppCompatActivity {

    private CardView primaryView;
    private CardView secondaryView;
    private CardView higherView;
    private CardView graduationView;

    private LinearLayout myAccountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_dashboard);

        primaryView = findViewById(R.id.teacher_primary);
        secondaryView = findViewById(R.id.teacher_secondary);
        higherView = findViewById(R.id.teacher_higher);
        graduationView = findViewById(R.id.teacher_graduation);

        myAccountLayout = findViewById(R.id.account_layout);


        myAccountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeachingMyAccount.class));

            }
        });

        primaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Master_PrimaryActivity.class));
            }
        });

        secondaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Master_SecondryActivity.class));
            }
        });
        higherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Master_HigherActivity.class));
            }
        });

        graduationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Master_GraduationActivity.class));
            }
        });

    }
}