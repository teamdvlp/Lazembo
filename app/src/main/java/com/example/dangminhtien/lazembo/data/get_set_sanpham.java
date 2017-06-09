package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Fragment.fragment_product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class get_set_sanpham {
Context context;
FirebaseDatabase database;
DatabaseReference databaseReference;
FirebaseStorage firebaseStorage;
StorageReference storageReference;
    get_image get_image;
    get_set_Khachhang get_set_khachhang;
    get_sanpham get_sanpham;
    get_sanphams get_sanphams;
    upload_image upload_image;
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

    public String upLoadImage (final ArrayList<Bitmap> bitmap, ArrayList<String> path) {
        for (int i = 0; i < bitmap.size(); i ++) {
            StorageReference storageReference2 = storageReference.child(path.get(i));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.get(i).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final ArrayList<String> urls = new ArrayList<String>();
            UploadTask uploadTask = storageReference2.putBytes(bytes);
                    final int finalI = i;
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Failed  " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests")Uri url = taskSnapshot.getDownloadUrl();
                        urls.add(url.toString());
                    if (finalI == bitmap.size()) {
                        upload_image.on_upload_image(urls);
                    }
                }
            });}

        return null;
    }

    public void getSanpham (final String masp) {

        final Sanpham[] sanpham = new Sanpham[1];
        if (masp != "") {
        databaseReference.child(masp).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    sanpham[0] = dataSnapshot.getValue(Sanpham.class);
                    get_sanpham.on_get_sanpham(sanpham[0]);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

    }

    public void get_sanphams (final ArrayList<String> masp) {
        Iterator<String> iterator = masp.iterator();
        final int[] count = {0};
        final ArrayList <Sanpham> sanphams = new ArrayList<Sanpham>();
        while (iterator.hasNext()) {
        getSanpham(iterator.next());
            set_on_get_sanpham(new get_sanpham() {
                @Override
                public void on_get_sanpham(Sanpham sanpham) {
                    count[0]++;
                    sanphams.add(sanpham);
                        if (count[0] == masp.size()) {
                    get_sanphams.on_get_sanphams(sanphams);
                }}
            });
        }}

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

    public void set_on_get_sanpham (get_sanpham get_sanpham) {
        this.get_sanpham = get_sanpham;
    }

    public Bitmap getImage (String path) throws IOException {
        final File localFile = File.createTempFile("images", "jpg");
        final Bitmap[] bitmap = new Bitmap[1];
        storageReference.child(path).getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                bitmap[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                localFile.delete();
                get_image.on_get_image(bitmap[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        return bitmap[0];
    }


    public void write_path_by_path(String path, String[] paths, int count) {
        String nameDM = "Danh mục sản phẩm/";
        for (int i = 0; i < count; i++) {
            nameDM += "/" + paths[i];
        }
        databaseReference = database.getReference(nameDM);
        databaseReference.child(path).setValue(path);
    }

    public void set_on_upload_image (upload_image upload_image) {
        this.upload_image = upload_image;
    }

    public void on_get_image (get_image get_image) {
        this.get_image = get_image;
    }

    public void on_getsanphams (get_sanphams get_sanphams) {
        this.get_sanphams = get_sanphams;
    }

    public interface get_image {
       public void on_get_image (Bitmap bitmap);
    }

    public interface get_sanpham {
        public void on_get_sanpham (Sanpham sanpham);
    }

    public interface get_sanphams {
        public void on_get_sanphams (ArrayList<Sanpham> sanphams);
    }

    public interface upload_image {
        public void on_upload_image (ArrayList<String> url);
    }
}
