package com.example.dangminhtien.lazembo.helper;

import android.content.Context;

import com.example.dangminhtien.lazembo.Fragment.Account_Fragment;
import com.example.dangminhtien.lazembo.data.Khachhang;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    public ArrayList<String> get_paths (ArrayList<Sanpham> sanphams) {
        Iterator<Sanpham> sanpham_iterator = sanphams.iterator();
        ArrayList<String> paths = new ArrayList<String>();
        while (sanpham_iterator.hasNext()) {
            Sanpham sanpham = sanpham_iterator.next();
            if (sanpham != null) {
                if (sanpham.getHinh() != null) {
                    paths.addAll(sanpham.getHinh());
                    for (int i = 0; i < paths.size(); i ++) {
                        String path = sanpham.getHinh().get(i);
                        paths.add(path);
                        break;
                    }}}}
        return paths;
    }

}
