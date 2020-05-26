package com.tanzible.teacherfinder.teachngActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.activity.OpenActivity;
import com.tanzible.teacherfinder.lerningActivity.LearningEditProfile;
import com.tanzible.teacherfinder.lerningActivity.LearningMyAccount;

public class TeachingMyAccount extends AppCompatActivity {

    private CardView editProfileView;
    private CardView logoutView;
    private CardView aboutView;
    private CardView helpView;
    private TextView uName,uEmail,uMobile;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_my_account);

        editProfileView = findViewById(R.id.cardViewEdit);
        logoutView = findViewById(R.id.cardViewLogout);
        aboutView = findViewById(R.id.cardViewAbout);
        helpView = findViewById(R.id.cardViewHelp);
        uName = findViewById(R.id.uesrName);
        uEmail = findViewById(R.id.uemail);
        uMobile = findViewById(R.id.uMobile);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        UserId = mAuth.getCurrentUser().getUid();


        DocumentReference documentReference = firestore.collection("Teaching").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                uMobile.setText(documentSnapshot.getString("Mobile"));
                uName.setText(documentSnapshot.getString("fName"));
                uEmail.setText(documentSnapshot.getString("email"));

            }
        });


        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });

        editProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LearningEditProfile.class));
            }
        });
    }

    private void showPopUp() {

        AlertDialog.Builder alert = new AlertDialog.Builder(TeachingMyAccount.this);
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
