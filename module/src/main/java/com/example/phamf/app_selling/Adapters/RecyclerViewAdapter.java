package com.example.phamf.app_selling.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamf.app_selling.Model.CartModel;
import com.example.phamf.app_selling.R;

import java.util.ArrayList;

/**
 * Created by phamf on 30-May-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    Context context;
    ArrayList<CartModel> list;
    RecyclerView recyclerView;

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View view = (View) v.getTag();
            setColor(view);
        }
    };
    public RecyclerViewAdapter(Context context, ArrayList<CartModel> list, RecyclerView recyclerView) {
        this.context = context;
        this.list = list;
        this.recyclerView = recyclerView;
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.childview_item_recycler_view_cart,parent,false);
        view.setOnClickListener(click);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        final CartModel model = list.get(position);
        holder.txt_price_product.setText("   Giá: "+String.valueOf(Integer.parseInt(model.getPrice_product())*model.getCount_product()) +" vnđ");
        holder.txt_name_product.setText("   " + model.getName_product());
        holder.img_image_product.setImageResource(model.getImage_product());
        holder.edt_count_product.setText("   Số lượng: "+String.valueOf(model.getCount_product())+ " sản phẩm");
        holder.txt_choose.setText(position+"");
    }

    private void setColor(View view){
        view.setBackgroundColor(context.getColor(R.color.colorPrimary));
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
