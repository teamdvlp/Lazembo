package com.example.dangminhtien.lazembo.helper;

import android.content.Context;

import com.example.dangminhtien.lazembo.activity.activity_phan_loai_sp;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_Khachhang;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by dangminhtien on 6/10/17.
 */

public class helper_phanloai_sp {
    private Context context;
    private FirebaseAuth auth;
    private Sanpham sanpham;
    public helper_phanloai_sp (Context context) {
        this.context = context;
        this.auth = FirebaseAuth.getInstance();
        get_sanpham();
     }

    public Sanpham get_sanpham() {
        this.sanpham = Sanpham.getInstance();
        return sanpham;
    }

    public void get_path_up_to_firebase(String[] cache) {
        // vì sản phầm được ghi trong mục idsp nằm bên trong mục sản phẩm nên cần phải add vô thêm
            if (sanpham != null) {
        int count = 0;
        for (int i = 0; i < cache.length; i++) {
            if (null == cache[i]) {
                cache[i] = "Sản phẩm";
                cache[i + 1] = sanpham.getIdsp();
                count = i + 1;
                break;
            }
        }
        up_firebase(sanpham, cache, count);
    }}

    public void up_firebase (Sanpham sanpham, String[] cache, int count) {
        get_set_sanpham get_set_sanpham = new get_set_sanpham(context);
        get_set_sanpham.upLoadSanpham(sanpham, sanpham.getIdsp());
        get_set_sanpham.write_path_by_path(sanpham.getIdsp(), cache, count);
        get_set_Khachhang get_set_khachhang = new get_set_Khachhang(context);
        get_set_khachhang.up_sp_to_khachhang(sanpham.getIdsp(), auth.getCurrentUser().getUid());
    }

}
