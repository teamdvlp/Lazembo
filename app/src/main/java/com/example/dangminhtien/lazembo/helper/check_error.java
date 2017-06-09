package com.example.dangminhtien.lazembo.helper;

import android.text.TextUtils;

/**
 * Created by dangminhtien on 6/8/17.
 */

public class check_error {

    public boolean check_email(String email) {
        return email.contains("@") && email.contains(".") && !TextUtils.isEmpty(email) && email.length() >= 5;
    }

    public boolean check_password(String password) {
        return password.length() >= 6;
    }

    public boolean check_contain_digit(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (TextUtils.isDigitsOnly(Character.toString(chars[i]))) {
                return true;
            }
        }
        return false;
//l
    }

    public boolean check_ho_ten (String ho_ten) {
        return !TextUtils.isEmpty(ho_ten) && ho_ten.contains(" ") && ho_ten.length() >= 8;
    }

    public boolean check_sdt (String sdt) {
        return check_contain_digit(sdt) && sdt.length() >= 9;
    }

    public boolean check_display_name (String display_name) {
        return TextUtils.isEmpty(display_name) && display_name.length() >= 6;
    }


}
