package com.tanzible.teacherfinder.lerningActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.activity.OpenActivity;

public class LearningDashboard extends AppCompatActivity {

    private CardView primaryLearningView;
    private CardView secondaryLearningView;
    private CardView higherLearningView;
    private CardView graduationLearningView;
    private LinearLayout myHomeLayout;
    private LinearLayout myAccountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_dashboard);

        primaryLearningView = findViewById(R.id.primary_learning);
        secondaryLearningView = findViewById(R.id.secondary_learning);
        higherLearningView = findViewById(R.id.higher_learning);
        graduationLearningView = findViewById(R.id.graduation_learning);
        myHomeLayout = findViewById(R.id.home_layout_learning);
        myAccountLayout = findViewById(R.id.account_layout_learning);


        primaryLearningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningPrimaryActivity.class));
            }
        });

        primaryLearningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningPrimaryActivity.class));
            }
        });

        primaryLearningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningPrimaryActivity.class));
            }
        });

        primaryLearningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningPrimaryActivity.class));
            }
        });

        myHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), OpenActivity.class));
            }
        });
        myAccountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningMyAccount.class));

            }
        });






    }
}
