package com.example.dangminhtien.lazembo.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.Fragment.fragment_product;
import com.example.dangminhtien.lazembo.helper.helper;
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
    upload_images upload_images;
    helper helper;
    get_images get_images;
    get_all_ma_sanpham get_all_ma_sanpham;

    public get_set_sanpham(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Sản phẩm");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        helper = new helper();
    }

    public void upLoadSanpham(Sanpham sanpham, String masp) {
        databaseReference.child(masp).setValue(sanpham);
    }

    public void upLoadImage (Bitmap bitmap, String path) {
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
                @SuppressWarnings("VisibleForTests")Uri url = taskSnapshot.getDownloadUrl();
                upload_image.on_upload_image(url.toString());
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Upload_images (ArrayList<Bitmap> bitmaps, ArrayList<String> paths) {
        final Iterator<String> path = paths.iterator();
        final Iterator<Bitmap> bitmap = bitmaps.iterator();
            while (bitmap.hasNext()) {
                upLoadImage(bitmap.next(), path.next());
                set_on_upload_image_listener(new upload_image() {
                    @Override
                    public void on_upload_image(String url) {
                        if (!bitmap.hasNext()) {
                            upload_images.on_upload_images();
                        }
                    }
                });
            }
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

    public void get_all_ma_sanpham () {
       final ArrayList<String> ma_sanpham_storage = new ArrayList<String>();
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data_snap_of_child = dataSnapshot.getChildren();
                Iterator<DataSnapshot> ma_sanphams = data_snap_of_child.iterator();
                while (ma_sanphams.hasNext()) {
                    ma_sanpham_storage.add(ma_sanphams.next().getKey().toString());
                }
                get_all_ma_sanpham.on_get_all_ma_sanpham(ma_sanpham_storage);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
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

    public ArrayList<Bitmap> getImages (final ArrayList<String> paths) {
        final ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        final Iterator<String> path = paths.iterator();
        final int[] count = {0};
        while (path.hasNext()) {
                try {
                    getImage(path.next());
                    set_on_get_image_listener(new get_image() {
                        @Override
                        public void on_get_image(Bitmap bitmap) {
                            count[0]++;
                            bitmaps.add(bitmap);
                            if (count[0] == paths.size()) {
                                get_images.on_get_images(bitmaps);
                            }
                        }
                    });
                } catch (IOException e) {
                }
            }

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
                bitmap[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
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

    public void set_on_get_all_ma_sanpham (get_all_ma_sanpham get_all_ma_sanpham) {
        this.get_all_ma_sanpham = get_all_ma_sanpham;
    }

    public void set_on_get_images_listener (get_images get_images) {
        this.get_images = get_images;
    }

    public void set_on_upoad_images_listener (upload_images on_upoad_images) {
        upload_images = on_upoad_images;
    }

    public void set_on_upload_image_listener (upload_image upload_image) {
        this.upload_image = upload_image;
    }

    public void set_on_get_image_listener (get_image get_image) {
        this.get_image = get_image;
    }

    public void set_on_get_sanphams_listener (get_sanphams get_sanphams) {
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
        public void on_upload_image (String url);
    }

    public interface upload_images {
        public void on_upload_images();
    }

    public interface get_images {
        void on_get_images (ArrayList<Bitmap> bitmaps);
    }

    public interface get_all_ma_sanpham {
        void on_get_all_ma_sanpham(ArrayList<String> ma_sanpham_storage);
    }
}