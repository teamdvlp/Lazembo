package com.example.phamf.app_selling.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamf.app_selling.Model.ProductInformationModel;
import com.example.phamf.app_selling.R;

import java.util.ArrayList;

/**
 * Created by phamf on 28-May-17.
 */

public class GridviewAdapter extends BaseAdapter {
    ArrayList<ProductInformationModel> list;
    Activity activity;

    public GridviewAdapter(ArrayList<ProductInformationModel> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ProductInformationModel product = list.get(position);

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.childview_item_gridview, parent, false);
            viewHolder.imgv_product = (ImageView) convertView.findViewById(R.id.imgv_product);
            viewHolder.txt_name_product = (TextView) convertView.findViewById(R.id.txt_name_product);
            viewHolder.txt_price_product = (TextView) convertView.findViewById(R.id.txt_price_product);
            viewHolder.txt_price_sale_off_product = (TextView) convertView.findViewById(R.id.txt_price_sale_off_product);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgv_product.setImageResource(product.getProduct_image());
        viewHolder.txt_name_product.setText(product.getName());
        viewHolder.txt_price_product.setText(product.getPrice());
        viewHolder.txt_price_sale_off_product.setText(product.getSale_off_price());

        return convertView;
    }

    private class ViewHolder {
        private TextView txt_name_product,txt_price_product,txt_price_sale_off_product;
        private ImageView imgv_product;
        private ViewHolder(){

        }
    }
}
