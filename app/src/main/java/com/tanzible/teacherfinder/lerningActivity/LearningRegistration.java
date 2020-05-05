package com.tanzible.teacherfinder.lerningActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.activity.DrawerActivity;
import com.tanzible.teacherfinder.activity.LogInActivity;
import com.tanzible.teacherfinder.teachngActivity.TeachingDashboard;

import java.util.HashMap;
import java.util.Map;

public class LearningRegistration extends AppCompatActivity {

    private EditText name,mobile,email, password,address,student_class,student_school;
    private RelativeLayout register_button;
    private CardView register_button_card_view;
    private TextView AlreadyLogin;
    FirebaseAuth mAuth;
    private ProgressBar progressBar;
    FirebaseFirestore firestore;
    String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_registration);

        setType();
        registerOnClick();
        inputChange();
        alreadyLogin();



    }

    private void alreadyLogin() {

        AlreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LearingLogIn.class));
            }
        });
    }


    @SuppressLint("ResourceType")
    private void registerButtonStyle() {

        if (password.getText().length() > 0 && email.getText().length() > 0) {
            if (!register_button.isFocusable()) {
                register_button.setFocusable(true);
                register_button.setClickable(true);
                register_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                register_button.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (register_button.isFocusable()) {
                register_button.setFocusable(false);
                register_button.setClickable(false);
                register_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                register_button.setBackgroundResource(0);
            }
        }
    }

    private void setType() {
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register_button = findViewById(R.id.register_button);
        register_button_card_view = findViewById(R.id.register_button_card_view);
        AlreadyLogin = findViewById(R.id.already_register_learning);
        address = findViewById(R.id.address);
        student_class = findViewById(R.id.student_class);
        student_school = findViewById(R.id.student_school_name);
        //progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), LearningDashboard.class));
            finish();
        }



    }

    private void registerOnClick() {
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String uname = name.getText().toString().trim();
                final String umobile = mobile.getText().toString().trim();
                final String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();
                final String uaddress = address.getText().toString().trim();
                final  String ustudent_class = student_class.getText().toString().trim();
                final String ustudent_school = student_school.getText().toString().trim();

                if (TextUtils.isEmpty(uname)){
                    name.setError(" Name is required");
                    return;
                }

                if (TextUtils.isEmpty(umobile)){
                    mobile.setError(" Mobile is required");
                    return;
                }
                if (TextUtils.isEmpty(uemail)){
                    email.setError(" Email is required");
                    return;
                }
                if (TextUtils.isEmpty(upassword)){
                    password.setError(" Password is required");
                    return;
                }
                if (upassword.length() < 6){
                    password.setError("Password must be greater than 6 digit");
                }
                if (TextUtils.isEmpty(uaddress)){
                    address.setError(" Address is required");
                    return;
                }
                if (TextUtils.isEmpty(ustudent_class)){
                    student_class.setError(" Class is required");
                    return;
                }
                if (TextUtils.isEmpty(ustudent_school)){
                    student_school.setError("School / College name is required");
                }
                // progressBar.setVisibility(View.VISIBLE);

                // register the user in firebase

                mAuth.createUserWithEmailAndPassword(uemail,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(LearningRegistration.this,"User Created",Toast.LENGTH_SHORT).show();
                            UserId = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("Learning").document(UserId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",uname);
                            user.put("Mobile",umobile);
                            user.put("email",uemail);
                            user.put("address",uaddress);
                            user.put("student_class",ustudent_class);
                            user.put("student_school",ustudent_school);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","onsuccess: useer created" + UserId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LearningDashboard.class));
                            finish();
                        } else
                        {
                            Toast.makeText(LearningRegistration.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //  progressBar.setVisibility(View.GONE);
                        }

                    }
                });



            }
        });
    }
    private void inputChange() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                registerButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                registerButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
