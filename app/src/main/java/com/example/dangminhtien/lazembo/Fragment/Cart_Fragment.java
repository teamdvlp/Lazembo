package com.example.dangminhtien.lazembo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dangminhtien.lazembo.R;
import com.example.dangminhtien.lazembo.adapter.adapter_sp_account;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by phamf on 04-Jun-17.
 */

public class Cart_Fragment extends Fragment {
    View view;
    private com.example.dangminhtien.lazembo.adapter.adapter_sp_account adapter_sp_account;
    private RecyclerView recycle_sp_account;
    private FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Cart_Fragment newInstance() {

        Bundle args = new Bundle();

        Cart_Fragment fragment = new Cart_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
