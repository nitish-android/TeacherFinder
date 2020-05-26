package com.tanzible.teacherfinder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.LearningDashboard;
import com.tanzible.teacherfinder.lerningActivity.LearningRegistration;
import com.tanzible.teacherfinder.teachngActivity.TeachingDashboard;
import com.tanzible.teacherfinder.teachngActivity.TeachingRegistration;

public class OpenActivity extends AppCompatActivity {


    private CardView teachingView;
    private CardView learningView;
    FirebaseAuth mAuth;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        teachingView = findViewById(R.id.teaching);
        learningView = findViewById(R.id.leraning);
        checkBox = findViewById(R.id.checkbox);

        SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");



        mAuth = FirebaseAuth.getInstance();









    teachingView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), TeachingRegistration.class));

        }
    });

        learningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LearningRegistration.class));
               
            }
        });



    }
}
