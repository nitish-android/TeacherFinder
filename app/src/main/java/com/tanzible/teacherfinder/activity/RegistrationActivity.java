package com.tanzible.teacherfinder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tanzible.teacherfinder.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,mobile,email, password;
    private RelativeLayout register_button;
    private CardView register_button_card_view;
    private TextView login_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setType();
        registerOnClick();
        inputChange();
        loginTextOnClick();


    }

    private void loginTextOnClick() {

        login_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LogInActivity.class));
                finish();
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
        login_text_view = findViewById(R.id.login_text_view);


           }

    private void registerOnClick() {
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegistrationActivity.this,DrawerActivity.class));
                if (name.getText().length() > 0 && mobile.getText().length() > 0 && email.getText().length() > 0 && password.getText().length() > 0) {
                    Toast.makeText(RegistrationActivity.this, name.getText() +" " +mobile.getText() + " " +email.getText() + " " + password.getText(), Toast.LENGTH_LONG).show();
                }
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
