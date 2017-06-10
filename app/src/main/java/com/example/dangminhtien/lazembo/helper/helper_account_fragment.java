package com.example.dangminhtien.lazembo.helper;

import android.content.Context;

import com.example.dangminhtien.lazembo.Fragment.Account_Fragment;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dangminhtien on 6/10/17.
 */

public class helper_account_fragment {
    Context context;

    public helper_account_fragment (Context context) {
        this.context = context;
    }

    public ArrayList<String> get_masp (Khachhang khachhang) {
        HashMap hashMap = khachhang.getSanphams();
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<String> masp = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            if (entry.getValue() != "" && iterator.hasNext()) {
                masp.add(entry.getValue());
            }
        }
        return masp;
    }

}
