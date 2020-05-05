package com.tanzible.teacherfinder.lerningActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.activity.DrawerActivity;
import com.tanzible.teacherfinder.activity.LogInActivity;

public class LearingLogIn extends AppCompatActivity {

    private EditText email, password;
    private TextView forgot_password_text_view;
    private RelativeLayout login_button;
    private CardView login_button_card_view;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learing_log_in);

        setType();
       // forgotPasswordOnClick();
        loginOnClick();
        inputChange();
    }

    private void setType() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgot_password_text_view = findViewById(R.id.learning_forgot_password_text_view);
        login_button = findViewById(R.id.login_button);
        login_button_card_view = findViewById(R.id.login_button_card_view);

        firebaseAuth = FirebaseAuth.getInstance();

        forgot_password_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText((LearingLogIn.this));
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder((LearingLogIn.this));
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your Email To Received Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(LearingLogIn.this, "Reset Link Sent to your email.", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LearingLogIn.this, "Error! Reset link not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });



                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }

    @SuppressLint("ResourceType")
    private void loginButtonStyle() {
        if (password.getText().length() > 0 && email.getText().length() > 0) {
            if (!login_button.isFocusable()) {
                login_button.setFocusable(true);
                login_button.setClickable(true);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                login_button.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (login_button.isFocusable()) {
                login_button.setFocusable(false);
                login_button.setClickable(false);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                login_button.setBackgroundResource(0);
            }
        }
    }



    private void loginOnClick() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();

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


                firebaseAuth.signInWithEmailAndPassword(uemail,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(LearingLogIn.this,"Logged in successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LearningDashboard.class));
                            finish();
                        }
                        else {
                            Toast.makeText(LearingLogIn.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
                loginButtonStyle();
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
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
