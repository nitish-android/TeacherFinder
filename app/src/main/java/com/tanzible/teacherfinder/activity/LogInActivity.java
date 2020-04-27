package com.tanzible.teacherfinder.activity;

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
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tanzible.teacherfinder.R;

public class LogInActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView forgot_password_text_view;
    private RelativeLayout login_button;
    private CardView login_button_card_view;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setType();
        forgotPasswordOnClick();
        loginOnClick();
        inputChange();
    }

    private void setType() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgot_password_text_view = findViewById(R.id.forgot_password_text_view);
        login_button = findViewById(R.id.login_button);
        login_button_card_view = findViewById(R.id.login_button_card_view);

        firebaseAuth = FirebaseAuth.getInstance();
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
    private void forgotPasswordOnClick() {
        forgot_password_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LogInActivity.this, getString(R.string.forgot_password), Toast.LENGTH_LONG).show();
            }
        });
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
                            Toast.makeText(LogInActivity.this,"Logged in successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(LogInActivity.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
