package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class get_set_Khachhang {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;
    private ondatachange ondatachange;
    public get_set_Khachhang(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Khách hàng/");
    }

    public void up_sp_to_khachhang (String masp, String makh) {
        databaseReference.child(makh).child("sanphams").push().setValue(masp);
    }

    public void set_khachhang(Khachhang khachhang) {
        databaseReference.child(khachhang.getUid()).setValue(khachhang);
    }

    public void get_khachhang(String ma_khachhang) {
        final Khachhang[] khachhang = {null};
        databaseReference.child(ma_khachhang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                khachhang[0] = dataSnapshot.getValue(Khachhang.class);
                ondatachange.ondatachange(khachhang[0]);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setOnDataChange (ondatachange onDataChange) {
        this.ondatachange = onDataChange;
    }

    public interface ondatachange {
        public void ondatachange(Khachhang khachhang);
    }

}
