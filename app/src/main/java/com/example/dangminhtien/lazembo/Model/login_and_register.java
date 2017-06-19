package com.example.dangminhtien.lazembo.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Fragment.Account_Fragment;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.HashMap;

public class login_and_register {
    private FirebaseAuth auth;
    private Context context;

    private login login;
    private register register;

    public login_and_register (Context context) {
            auth = FirebaseAuth.getInstance();
            this.context = context;
        }

    public void login (String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                // dùng để yêu cần fragment account reset lại
                Account_Fragment.refresh = 1;
                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                login.on_login(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Email hoặc mật khẩu không đúng" + e, Toast.LENGTH_LONG).show();
                login.on_login(false);
            }
        });
    }

    public void register (final String email, String password, final String display_name, final String ho_ten, final String sdt) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                UserProfileChangeRequest change_info_user = new UserProfileChangeRequest.Builder().setDisplayName(display_name).build();
                authResult.getUser().updateProfile(change_info_user);
                String uid = authResult.getUser().getUid();
                // nếu không có safe key thì khi ghi lên firebase mục sanphams sẽ mất
                HashMap<String,String> safe_key = new HashMap<String, String>();
                safe_key.put("safe_ket", "0");
                boolean is_seller = false;
                Khachhang khachhang = new Khachhang(ho_ten, email,sdt, is_seller, safe_key, uid);
                get_set_Khachhang get_set_khachhang = new get_set_Khachhang(context);
                get_set_khachhang.set_khachhang(khachhang);
                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                register.on_register(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                register.on_register(false);
            }
        });
    }

    public void set_on_login (login  login) {
        this.login = login;
    }

    public void set_on_register (register register) {
        this.register = register;
    }

    public interface login {
        public void on_login(boolean is_success);
    }

    public interface register {
        public void on_register(boolean is_success);
    }

}
