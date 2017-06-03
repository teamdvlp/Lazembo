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
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class activity_register extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    // UI references.
    private AutoCompleteTextView txt_email;
    private EditText txt_password, txt_password_again, txt_HovaTen, txt_sdt;
    private View mProgressView;
    private Button btn_register, btn_signin_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        addControls();
    }

    private void addControls() {
        txt_password_again = (EditText) findViewById(R.id.txt_password_again_register);
        txt_email = (AutoCompleteTextView) findViewById(R.id.txt_email_register);
        btn_signin_register = (Button) findViewById(R.id.btn_Singin_register);
        txt_password = (EditText) findViewById(R.id.txt_password_register);
        txt_HovaTen = (EditText) findViewById(R.id.txt_HovaTen);
        txt_sdt = (EditText) findViewById(R.id.txt_sdt);
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
        btn_signin_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_register.this, activity_Login_Register.class));
            }
        });
        btn_register = (Button) findViewById(R.id.btn_register_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.register_progress);
    }

    private void attemptLogin() {

        // Reset errors.
        txt_email.setError(null);
        txt_password.setError(null);
        txt_password_again.setError(null);
        txt_sdt.setError(null);
        txt_HovaTen.setError(null);
        // Store values at the time of the login attempt.
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        boolean Continue = true;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            txt_password.setError("Your password is empty");
            focusView = txt_password;
            Continue = !true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            txt_email.setError("Your email is empty");
            focusView = txt_email;
            Continue = !true;
        } else if (!isEmailValid(email)) {
            txt_email.setError("Your email is invalid");
            focusView = txt_email;
            Continue = !true;
        }

        // check password again
        if (!txt_password_again.getText().toString().equals(txt_password.getText().toString())) {
            txt_password_again.setError("Your password is not match");
            Continue = !true;
        }

        if (TextUtils.isEmpty(txt_HovaTen.getText().toString())) {
            txt_HovaTen.setError("Your text is empty");
            Continue = !true;
        }

        if (TextUtils.isEmpty(txt_sdt.getText())) {
            txt_sdt.setError("Your text is empty");
        }

        if (Continue == false) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            process_Register();
            showProgress(true);
        }
    }

    private void process_Register() {
        firebaseAuth.createUserWithEmailAndPassword(txt_email.getText().toString(), txt_password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do somethings
                mProgressView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                String uid = "";
                uid = authResult.getUser().getUid();
                Khachhang khachhang = new Khachhang(txt_HovaTen.getText().toString(), txt_email.getText().toString(), txt_sdt.getText().toString(), false, new ArrayList<String>(), uid);
                get_set_Khachhang get_set_khachhang = new get_set_Khachhang(activity_register.this);
                get_set_khachhang.set_khachhang(khachhang);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mProgressView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 6;
    }

}
