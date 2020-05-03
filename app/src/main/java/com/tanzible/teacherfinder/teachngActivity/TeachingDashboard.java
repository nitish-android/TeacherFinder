package com.tanzible.teacherfinder.teachngActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import com.tanzible.teacherfinder.R;

public class TeachingDashboard extends AppCompatActivity {

    private CardView primaryView;
    private CardView secondaryView;
    private CardView higherView;
    private CardView graduationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_dashboard);

        primaryView = findViewById(R.id.primary);
        secondaryView = findViewById(R.id.secondary);
        higherView = findViewById(R.id.higher);
        graduationView = findViewById(R.id.graduation);

    }
}
