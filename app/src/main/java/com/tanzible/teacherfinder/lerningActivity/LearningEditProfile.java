package com.tanzible.teacherfinder.lerningActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.tanzible.teacherfinder.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class LearningEditProfile extends AppCompatActivity {

    private CircleImageView profile_Image;
    private TextView tv_Name;
    private TextView tv_mobile;
    private TextView tv_email;
    private TextView tv_address;
    private TextView tv_student_class;
    private TextView tv_school_name;
    TextView verify_number;
    private Toolbar toolbar;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_edit_profile);





        setType();

    }


    private void setType() {
        profile_Image = findViewById(R.id.profile_image);
        tv_Name = findViewById(R.id.tv_name);
        tv_mobile = findViewById(R.id.tv_mobile_no);
        tv_email = findViewById(R.id.tv_email);
        tv_address = findViewById(R.id.tv_address);
        tv_student_class = findViewById(R.id.tv_student_class);
        tv_school_name = findViewById(R.id.tv_school_name);
        verify_number = findViewById(R.id.verify_mobileno);
        

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        UserId = mAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firestore.collection("Learning").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                tv_mobile.setText(documentSnapshot.getString("Mobile"));
                tv_Name.setText(documentSnapshot.getString("fName"));
                tv_email.setText(documentSnapshot.getString("email"));
                tv_address.setText(documentSnapshot.getString("address"));
                tv_student_class.setText(documentSnapshot.getString("student_class"));
                tv_school_name.setText(documentSnapshot.getString("student_school"));



            }
        });






    }

}
