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

import com.example.dangminhtien.lazembo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Login_Register extends AppCompatActivity {


    FirebaseAuth firebaseAuth;

    // UI references.
    private AutoCompleteTextView txt_email;
    private EditText txt_password;
    private View mProgressView;
    private Button btn_signin, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        firebaseAuth = FirebaseAuth.getInstance();
        addControls();

    }

    private void addControls() {
        txt_email = (AutoCompleteTextView) findViewById(R.id.txt_email);
        btn_register = (Button) findViewById(R.id.btn_register_register);
        txt_password = (EditText) findViewById(R.id.txt_password);
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

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_Login_Register.this, activity_register.class));
            }
        });
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        txt_email.setError(null);
        txt_password.setError(null);

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

        if (Continue == false) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            process_Signin();
        }
    }

    private void process_Signin() {
        firebaseAuth.signInWithEmailAndPassword(txt_email.getText().toString(), txt_password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do somethings
                mProgressView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), firebaseAuth.getCurrentUser().getProviderId() + "\n" +
                                firebaseAuth.getCurrentUser().getEmail() + "\n" + firebaseAuth.getCurrentUser().getDisplayName()
                        , Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                mProgressView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".") && email.length() >= 5;
    }

    private boolean check_contain_digit(String str) {
        String[] strs = str.split("");
        for (int i = 0; i < strs.length; i++) {
            Toast.makeText(getApplicationContext(), strs[i], Toast.LENGTH_SHORT).show();
            if (TextUtils.isDigitsOnly(strs[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 6 && check_contain_digit(password);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
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
}

//    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
//        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(activity_Login_Register.this,
//                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
//
//        txt_email.setAdapter(adapter);

