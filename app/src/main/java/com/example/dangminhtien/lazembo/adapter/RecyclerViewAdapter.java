package com.example.dangminhtien.lazembo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Sanpham;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;

import java.util.ArrayList;

/**
 * Created by phamf on 30-May-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    private Context context;
    private ArrayList<Sanpham> list;
    private ArrayList<Bitmap> bitmaps;
    //RecyclerView để set Sự kiện click item ? OK ?
    private RecyclerView recyclerView;
    private get_set_sanpham get_set_sanpham;


    public RecyclerViewAdapter(Context context, ArrayList<Sanpham> list, RecyclerView recyclerView, com.example.dangminhtien.lazembo.data.get_set_sanpham get_set_sanpham) {
        this.context = context;
        this.list = list;
        this.recyclerView = recyclerView;
        this.get_set_sanpham = get_set_sanpham;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.childview_item_recycler_view_cart,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        final Sanpham model = list.get(position);
        //TRUYỀN LIST URL dô Path để lấy list ? OK ?
        bitmaps = get_set_sanpham.getImages(model.getHinh());
        //SET thằng hình đầu tiên làm avatar cho sản phẩm
        holder.img_image_product.setImageBitmap(bitmaps.get(0));
        //GIÁ này nhân cho số lượng,
        holder.txt_price_product.setText("   Giá: " + String.valueOf(model.getGiasp()*model.getSoluong()) +" vnđ");
        holder.txt_name_product.setText("  " + model.getTensp());
        holder.edt_count_product.setText(String.valueOf(model.getSoluong()));

//        holder.txt_ten_sp_account.setText(sanphams.get(position).getTensp());
//        DecimalFormat decimalFormat = new DecimalFormat("###################.###################");
//        holder.txt_gia_sp_account.setText(decimalFormat.format(sanphams.get(position).getGiasp()));
//        holder.img_hinhsp_account.setImageBitmap(bitmaps.get(position));
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        ImageView img_image_product;
        TextView txt_name_product,txt_price_product,txt_choose;
        EditText edt_count_product;

//        TextView btn_plus,btn_subtract;


        public DataViewHolder(View itemView) {
            super(itemView);
            img_image_product = (ImageView) itemView.findViewById(R.id.child_img_image_product);
            txt_name_product = (TextView) itemView.findViewById(R.id.child_txt_name_product);
            txt_price_product= (TextView) itemView.findViewById(R.id.child_txt_price_product);
            txt_choose = (TextView) itemView.findViewById(R.id.child_txt_choose);
            edt_count_product= (EditText) itemView.findViewById(R.id.child_edt_count_product);
            itemView.setTag(txt_choose);
        }
    }
}
