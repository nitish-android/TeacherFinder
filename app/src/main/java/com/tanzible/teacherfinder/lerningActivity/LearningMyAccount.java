package com.tanzible.teacherfinder.lerningActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.activity.OpenActivity;

public class LearningMyAccount extends AppCompatActivity {

    private CardView editProfileView;
    private CardView logoutView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_my_account);

        editProfileView = findViewById(R.id.cardViewEdit);
        logoutView = findViewById(R.id.cardViewLogout);

        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });

        editProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearningEditProfile.class));
            }
        });




    }

    private void showPopUp() {

        AlertDialog.Builder alert = new AlertDialog.Builder(LearningMyAccount.this);
        alert.setMessage("Are you sure you wnat to Logout?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener()                 {

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                }).setNegativeButton("Cancel", null);

        AlertDialog alert1 = alert.create();
        alert1.show();


    }



    private void logout() {
        FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), OpenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();

    }
}
