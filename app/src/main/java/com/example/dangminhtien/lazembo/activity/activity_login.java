package com.example.dangminhtien.lazembo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Fragment.Account_Fragment;
import com.example.dangminhtien.lazembo.Model.login_and_register;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.helper.check_error;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {

    private check_error check_error;
    private login_and_register login;

    private AutoCompleteTextView txt_email;
    private EditText txt_password;
    private View mProgressView;
    private Button btn_signin, btn_register;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        add_class();
        add_controls();
        add_events();
    }

    private void add_events() {
        txt_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_login.this, activity_register.class));
            }
        });

        btn_signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        login.set_on_login(new login_and_register.login() {
            @Override
            public void on_login(boolean is_success) {
                showProgress(false);
            }
        });

    }

    private void add_controls() {
        txt_email = (AutoCompleteTextView) findViewById(R.id.txt_email);
        btn_register = (Button) findViewById(R.id.btn_register_register);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void get_text_from_edit_text () {
        password = txt_password.getText().toString();
        email = txt_email.getText().toString();
    }

    private void add_class() {
        login = new login_and_register(getApplicationContext());
    }

    private void attemptLogin() {
            showProgress(true);
            get_text_from_edit_text();
            login.login(email, password);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}

