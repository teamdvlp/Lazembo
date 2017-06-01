package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.activity.Main_Lazembo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class Danhmucsp {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Context context;

    private Spinner sp;
    public Danhmucsp(Context context, Spinner sp) {
        this.context = context;
        this.sp = sp;
        database =  FirebaseDatabase.getInstance();
    }

    public ArrayList<String>  getChild (String[] link) {
        String nameDM = "Danh mục sản phẩm";
            for (int i = 0; i < link.length; i++) {
                  nameDM += "/" +link[i];
        }
        final ArrayList<String> strings = new ArrayList<String>();
        databaseReference = database.getReference(nameDM);
        databaseReference.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                while (dataSnapshotIterator.hasNext()) {
                    String str = dataSnapshotIterator.next().getKey().toString();
                    if (str.equalsIgnoreCase("sản phẩm")) {
                        break;
                    } else {
                        strings.add(str);
                    }
                }
                datachanged mainLazembo = (Main_Lazembo) context;
                mainLazembo.onDatachanged(strings, context, sp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return strings;
    }

        public boolean haschild (String[] link) {
            final boolean haschild[] = new boolean[2];
            String nameDm = "Danh mục sản phẩm";
            for (int i = 0; i < link.length; i++) {
                nameDm += "/" +link[i];
            }
            databaseReference = database.getReference(nameDm);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    haschild[1] = dataSnapshot.hasChildren();
                    Toast.makeText(context, dataSnapshot.hasChildren()  + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return haschild[1];
        }

    public interface datachanged {
        public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp);
    }

}
