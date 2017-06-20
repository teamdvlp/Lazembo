package com.example.dangminhtien.lazembo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.dangminhtien.lazembo.custom_view.*;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.data.Danhmucsp;
import com.example.dangminhtien.lazembo.data.get_set_sanpham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unnamed.b.atv.model.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    static boolean continues = true;
    static boolean back_finish = false;
    ImageView img_introView;
    Toolbar toolbar;
    DrawerLayout drawer_container;
    NavigationView nav_list_goods;
    ArrayList<String> list_group_title;
    private ScrollView expan_custom;
    private get_set_sanpham get_set_sanpham;
    private Danhmucsp danhmucsp;
    tiendvlp_expan_tree_listview tiendvlp_expan_tree_listview;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    HashMap<String, ArrayList<String>>  list_sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        danhmucsp = new Danhmucsp(MainActivity.this, null);
        databaseReference = firebaseDatabase.getReference();
        addControls();
        addEvents();

        img_introView.setVisibility(View.GONE);
    }

    private void addEvents() {
    }

    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        drawer_container = (DrawerLayout) findViewById(R.id.drawer_container);
        nav_list_goods = (NavigationView) findViewById(R.id.nav_list_goods);
        img_introView = (ImageView) findViewById(R.id.img_introView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer_container,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // khi vừa mở nav drawer thì sẽ lấy những danh mục đầu tiên trước
                // vì thằng getChild đã bắt đầu từ danh mục sản phẩm nên điền là ""
                danhmucsp.getChild(new String[]{""});
                danhmucsp.set_on_datachanged_listener(new Danhmucsp.datachanged() {
                    @Override
                    public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp) {
                        for (int i = 0; i < arr.size(); i++) {
                            tiendvlp_expan_tree_listview.add_tree_node(new tree_node("/" + arr.get(i),arr.get(i) ));
                        }
                        tiendvlp_expan_tree_listview.init();
                    }
                });
            }
        };
        drawer_container.setDrawerListener(toggle);
        toggle.syncState();
        list_group_title = new ArrayList<>();
        list_sub_title = new HashMap<>();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_parent_nav);
        tiendvlp_expan_tree_listview = new tiendvlp_expan_tree_listview(MainActivity.this, MainActivity.this);
        linearLayout.addView(tiendvlp_expan_tree_listview);
        tiendvlp_expan_tree_listview.hide_all();

        tiendvlp_expan_tree_listview.set_on_tree_node_click_listener(new tiendvlp_expan_tree_listview.on_tree_node_click() {
            @Override
            public void on_click(final tree_node treeNode, boolean is_hide) {
                danhmucsp.getChild(new String[]{treeNode.getNode_path()});
                danhmucsp.set_on_datachanged_listener(new Danhmucsp.datachanged() {
                    @Override
                    public void onDatachanged(ArrayList<String> arr, Context context, Spinner sp) {
                        for (int i = 0; i < arr.size(); i++) {
//                            Toast.makeText(getApplicationContext(), arr.get(i), Toast.LENGTH_SHORT).show();
                            tiendvlp_expan_tree_listview.add_tree_node(new tree_node(treeNode.getNode_path() + "/" + arr.get(i), arr.get(i)));
                        }
                        if (null != arr && 0 != arr.size()) {
                            tiendvlp_expan_tree_listview.init();
                        } else {
                            on_child_last_click(treeNode);
                        }

                    }
                });
            }

            @Override
            public void on_child_last_click(tree_node tree_node) {
                Toast.makeText(getApplicationContext(), tree_node.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void changes(Context context, Class classes){
        startActivity(new Intent(context,classes));
    }

}

