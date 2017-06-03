package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Fragment.fragment_product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class get_set_sanpham {
Context context;
FirebaseDatabase database;
DatabaseReference databaseReference;
FirebaseStorage firebaseStorage;
StorageReference storageReference;
    public get_set_sanpham(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Sản phẩm");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }

    public void upLoadSanpham(Sanpham sanpham, String masp) {
            databaseReference.child(masp).setValue(sanpham);
        }

    public String upLoadImage (Bitmap bitmap, String path) {
            StorageReference storageReference2 = storageReference.child(path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            UploadTask uploadTask = storageReference2.putBytes(bytes);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Failed  " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            });

        return storageReference2.getPath();
    }

    public Sanpham getSanpham (String masp) {

        final Sanpham[] sanpham = new Sanpham[1];

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sanpham[0] = dataSnapshot.getValue(Sanpham.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            return sanpham[0];
    }

    public ArrayList<Bitmap> getImages (String[] path) {
        final ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (int i = 0; i < path.length; i ++) {
            storageReference.child(path[i]).getBytes(2048).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmaps.add(bitmap);
                    fragment_product fragment_layout_sp = new fragment_product();
//                    fragment_layout_sp.add(bitmaps);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, e.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
        Toast.makeText(context, "aa", Toast.LENGTH_SHORT).show();

        return bitmaps;
    }

    public Bitmap getImage (String path) throws IOException {
        final File localFile = File.createTempFile("images", "jpg");
        final Bitmap[] bitmap = new Bitmap[1];
        storageReference.child("Sản phẩm/0127171496284064999").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                bitmap[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                localFile.delete();
                notifiDataChange fragment_layout_sp = new fragment_product();
                fragment_layout_sp.notifi(bitmap[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        return bitmap[0];
    }

    public ArrayList<Sanpham> getSanphams (String maDm) {
        final ArrayList<Sanpham> sanphams = new ArrayList<Sanpham>();
        databaseReference.child(maDm).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sanphams.add(dataSnapshot.getValue(Sanpham.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return sanphams;
    }

    public void write_path_by_path(String path, String[] paths) {
        String nameDM = "Danh mục sản phẩm/";
        for (int i = 0; i < paths.length - 1; i++) {
            nameDM += "/" + paths[i];
        }
        databaseReference = database.getReference(nameDM);
        databaseReference.child(paths[paths.length - 1]).setValue(path);
    }

    public interface notifiDataChange {
        void notifi(Bitmap bitmap);
    }
}
