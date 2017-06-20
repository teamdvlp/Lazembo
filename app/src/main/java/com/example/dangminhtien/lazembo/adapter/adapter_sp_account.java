package com.example.dangminhtien.lazembo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.example.dangminhtien.lazembo.activity.activity_product;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Sanpham;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapter_sp_account extends RecyclerView.Adapter<adapter_sp_account.view_holder> implements View.OnClickListener{
    private Context context;
    private ArrayList<Bitmap> bitmaps;
    public static ArrayList<Sanpham> sanphams;
    RecyclerView recyclerView;

    // chỉ cần truyền vào recycler view mà ta đã findviewbyid
    public adapter_sp_account(Context context, ArrayList<Sanpham> sanphams, ArrayList<Bitmap> bitmaps, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.sanphams = sanphams;
        this.bitmaps = bitmaps;
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.childview_item_recycle_account, parent, false);
        view.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildLayoutPosition(v);
        context.startActivity(new Intent(context, activity_product.class).putExtra("position_account",position));
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
