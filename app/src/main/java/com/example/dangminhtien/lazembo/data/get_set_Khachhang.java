package com.example.dangminhtien.lazembo.data;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dangminhtien on 6/3/17.
 */

public class get_set_Khachhang {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public get_set_Khachhang(Context context) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Khách hàng/");
    }

    public void set_khachhang(Khachhang khachhang) {
        databaseReference.child(khachhang.getUid()).setValue(khachhang);
    }

    public void get_khachhang() {

    }
}
