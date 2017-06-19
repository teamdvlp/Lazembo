package com.example.dangminhtien.lazembo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class adapter_sp_account extends RecyclerView.Adapter<adapter_sp_account.view_holder> {
    private ArrayList<String> path_sp;
    private Context context;
    private ArrayList<Bitmap> bitmaps;
    private ArrayList<Sanpham> sanphams;

    public adapter_sp_account(Context context, ArrayList<Sanpham> sanphams, ArrayList<Bitmap> bitmaps) {
        this.context = context;
        this.sanphams = sanphams;
        this.bitmaps = bitmaps;

    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.childview_item_recycle_account, parent, false);
        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(final view_holder holder, int position) {
        holder.txt_ten_sp_account.setText(sanphams.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###################.###################");
        holder.txt_gia_sp_account.setText(decimalFormat.format(sanphams.get(position).getGiasp()));
        holder.img_hinhsp_account.setImageBitmap(bitmaps.get(position));
    }

    @Override
    public int getItemCount() {
        return sanphams.size() ;
    }

    class view_holder extends RecyclerView.ViewHolder {
        ImageView img_hinhsp_account;
        Button btn_popup_menu_account;
        TextView txt_gia_sp_account, txt_ten_sp_account;
        public view_holder(View itemView) {
            super(itemView);
            img_hinhsp_account = (ImageView) itemView.findViewById(R.id.img_hinh_sp_account);
            btn_popup_menu_account = (Button) itemView.findViewById(R.id.btn_pop_up_menu_account);
            txt_gia_sp_account = (TextView) itemView.findViewById(R.id.txt_gia_sp_account);
            txt_ten_sp_account = (TextView) itemView.findViewById(R.id.txt_ten_sp_account);
        }
    }
}
