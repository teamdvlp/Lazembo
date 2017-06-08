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

import com.example.dangminhtien.lazembo.Model.login_and_register;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.example.dangminhtien.lazembo.helper.check_error;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class activity_register extends AppCompatActivity {
    private login_and_register register;
    private check_error check_error;

    private AutoCompleteTextView txt_email_register;
    private EditText txt_password_register, txt_password_again_register, txt_HovaTen_register, txt_sdt_register, txt_displayname_register;
    private View mProgressView;
    private Button btn_register, btn_signin_register;
    private String email, password, sdt, display_name, password_again, ho_ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addClass();
        addControls();
        addEvents();

    }

    private void addClass() {
        check_error = new check_error();
        register = new login_and_register(getApplicationContext());
    }

    private void addEvents() {
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
                startActivity(new Intent(activity_register.this, activity_login.class));
            }
        });
        btn_register = (Button) findViewById(R.id.btn_register_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        register.set_on_register(new login_and_register.register() {
            @Override
            public void on_register(boolean is_success) {
                showProgress(false);
            }
        });
    }

    private void addControls() {
        txt_displayname_register = (EditText) findViewById(R.id.txt_display_name_register);
        txt_password_again_register = (EditText) findViewById(R.id.txt_password_again_register);
        txt_email_register = (AutoCompleteTextView) findViewById(R.id.txt_email_register);
        btn_signin_register = (Button) findViewById(R.id.btn_Singin_register);
        txt_password_register = (EditText) findViewById(R.id.txt_password_register);
        txt_HovaTen_register = (EditText) findViewById(R.id.txt_HovaTen_register);
        txt_sdt_register = (EditText) findViewById(R.id.txt_sdt_register);
        mProgressView = findViewById(R.id.register_progress);

    }

    private void get_text_from_edit_text () {
        display_name = txt_displayname_register.getText().toString();
        sdt = txt_sdt_register.getText().toString();
        ho_ten = txt_HovaTen_register.getText().toString();
        email = txt_email_register.getText().toString();
        password = txt_password_register.getText().toString();
        password_again = txt_password_again_register.getText().toString();
    }

    private void attemptLogin() {
           reset_error();
           get_text_from_edit_text();
        boolean check_result = check_error();

        if (check_result)
            showProgress(true);
            register.register(email, password, display_name, ho_ten, sdt);
    }

    private boolean check_error () {
            boolean check_result = true;
            View focus_view = null;
        if (check_result && check_error.check_email(email)) {
            txt_email_register.setError("Email phải chứa các ký tự: '@', '.', và có trên 5 ký tự");
            focus_view = txt_email_register;
            check_result =  false;
        }

        if (check_result && !check_error.check_password(password)) {
            txt_password_register.setError("Mật khẩu phải chứa ký tự số, và từ 6 ký tự đổ lên");
            focus_view = txt_password_register;
            check_result =  false;
        }

        if (check_result && !password_again.equals(password)) {
            txt_password_again_register.setError("Không trùng khớp với mật khẩu bạn đã nhập ở trên");
            focus_view = txt_password_again_register;
            check_result =  false;
        }

        if (check_result && !check_error.check_ho_ten(ho_ten)) {
            txt_HovaTen_register.setError("Họ và tên không hợp lệ");
            focus_view = txt_HovaTen_register;
            check_result =  false;
        }

        if (check_result && !check_error.check_sdt(sdt)) {
            txt_sdt_register.setError("Số điện thoại phải từ 9 ký tự đổ lên");
            focus_view = txt_sdt_register;
            check_result =  false;
        }

        if (check_result && !check_error.check_display_name(display_name)) {
            txt_displayname_register.setError("Tên hiển thị phải từ 6 ký tự đổ lên");
            focus_view = txt_displayname_register;
            check_result =  false;
        }
            focus_view.requestFocus();
        return check_result;
    }

    private void reset_error () {
        txt_email_register.setError(null);
        txt_password_register.setError(null);
        txt_password_again_register.setError(null);
        txt_sdt_register.setError(null);
        txt_HovaTen_register.setError(null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


}
