package com.example.phamf.app_selling.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.phamf.app_selling.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by phamf on 25-May-17.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<String> arrayList;
    HashMap<String,ArrayList<String>> hashMap;

    public ExpandableListViewAdapter(Context context, ArrayList<String> arrayList, HashMap<String, ArrayList<String>> hashMap) {
        this.context = context;
        this.arrayList = arrayList;
        this.hashMap = hashMap;
    }

    @Override
    public int getGroupCount() {
        return arrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMap.get(arrayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMap.get(arrayList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.childview_explv_group_view,null);
        TextView txtv_title = (TextView) convertView.findViewById(R.id.txtv_View);
        txtv_title.setText("   " + header);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child = (String) getChild(groupPosition,childPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.childview_explv_child_view,null);
        TextView txtv_sub_title = (TextView) convertView.findViewById(R.id.txtv_View_child);
        txtv_sub_title.setText("     " + child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
