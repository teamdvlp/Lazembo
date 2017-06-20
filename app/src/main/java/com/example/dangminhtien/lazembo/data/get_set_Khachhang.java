package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class get_set_Khachhang {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;
    private get_khachhang get_khachhang;
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
                get_khachhang.on_get_khachhang(khachhang[0]);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public ArrayList<Sanpham> get_all_sanpham_khachhang (String maKh) {
        final ArrayList<Sanpham> sanphams = new ArrayList<Sanpham>();
        final get_set_sanpham get_set_sanpham = new get_set_sanpham(context);
        databaseReference.child(maKh).child("sanphams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getKey() != "safe_key") {
                get_set_sanpham.getSanpham((String) dataSnapshot.getKey());
                get_set_sanpham.set_on_get_sanpham_listener(new get_set_sanpham.get_sanpham() {
                    @Override
                    public void on_get_sanpham(Sanpham sanpham) {
                        sanphams.add(sanpham);
                    }
                });
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return sanphams;
    }

    public void set_on_get_khachhang_listener (get_khachhang get_khachhang) {
        this.get_khachhang = get_khachhang;
    }



    public interface get_khachhang {
        public void on_get_khachhang(Khachhang khachhang);
    }
}
