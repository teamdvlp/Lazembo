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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class activity_register extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    // UI references.
    private AutoCompleteTextView txt_email_register;
    private EditText txt_password_register, txt_password_again_register, txt_HovaTen_register, txt_sdt_register, txt_displayname_register;
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
        txt_displayname_register = (EditText) findViewById(R.id.txt_display_name_register);
        txt_password_again_register = (EditText) findViewById(R.id.txt_password_again_register);
        txt_email_register = (AutoCompleteTextView) findViewById(R.id.txt_email_register);
        btn_signin_register = (Button) findViewById(R.id.btn_Singin_register);
        txt_password_register = (EditText) findViewById(R.id.txt_password_register);
        txt_HovaTen_register = (EditText) findViewById(R.id.txt_HovaTen_register);
        txt_sdt_register = (EditText) findViewById(R.id.txt_sdt_register);
        txt_password_register.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        txt_email_register.setError(null);
        txt_password_register.setError(null);
        txt_password_again_register.setError(null);
        txt_sdt_register.setError(null);
        txt_HovaTen_register.setError(null);
        // Store values at the time of the login attempt.

        boolean Continue = true;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(txt_password_register.getText().toString()) || !isPasswordValid(txt_password_again_register.getText().toString())) {
            txt_password_register.setError("Your password is empty");
            focusView = txt_password_register;
            Continue = !true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(txt_email_register.getText().toString())) {
            txt_email_register.setError("Your email is empty");
            focusView = txt_email_register;
            Continue = !true;
        } else if (!isEmailValid(txt_email_register.getText().toString())) {
            txt_email_register.setError("Your email is invalid");
            focusView = txt_email_register;
            Continue = !true;
        }

        // check password again
        if (!txt_password_again_register.getText().toString().equals(txt_password_register.getText().toString())) {
            txt_password_again_register.setError("Your password is not match");
            Continue = !true;
        }

        if (TextUtils.isEmpty(txt_HovaTen_register.getText().toString())) {
            txt_HovaTen_register.setError("Your text is empty");
            Continue = !true;
        }

        if (TextUtils.isEmpty(txt_sdt_register.getText())) {
            txt_sdt_register.setError("Your text is empty");
        }

        if (TextUtils.isEmpty(txt_displayname_register.getText())) {
            txt_displayname_register.setError("Your text is empty");
        }

        if (Continue == false) {
            focusView.requestFocus();
        } else {
            process_Register();
            showProgress(true);
        }
    }

    private void process_Register() {
        firebaseAuth.createUserWithEmailAndPassword(txt_email_register.getText().toString(), txt_password_register.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do somethings
                UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(txt_displayname_register.getText().toString()).build();
                authResult.getUser().updateProfile(changeRequest);
                String uid = authResult.getUser().getUid();
                Khachhang khachhang = new Khachhang(txt_HovaTen_register.getText().toString(), txt_email_register.getText().toString(), txt_sdt_register.getText().toString(), false, new ArrayList<String>(), uid);
                get_set_Khachhang get_set_khachhang = new get_set_Khachhang(activity_register.this);
                get_set_khachhang.set_khachhang(khachhang);
                mProgressView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mProgressView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean check_contain_digit(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (TextUtils.isDigitsOnly(Character.toString(chars[i]))) {
                return true;
            }
        }
        return false;
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
