package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toolbar;

import com.tmsca.sagarwal.tmscaapp.R;
import com.tmsca.sagarwal.tmscaapp.Recycler.Item;
import com.tmsca.sagarwal.tmscaapp.Recycler.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MathFragment extends Fragment{

    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private EditText search;


    ScaleInAnimationAdapter scaleInAnimationAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.math, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        //search = (EditText)rootView.findViewById(R.id.search)
        getActivity().setTitle("Math");
        itemAdapter = new ItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(itemAdapter);
        scaleInAnimationAdapter.setDuration(500);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        loadItems();
        return rootView;
    }

    void loadItems(){
        itemList.add(new Item("Quadratics", "Learn about quadratic functions and parabolas!", "https://ka-perseus-images.s3.amazonaws.com/1da3a0868e6253b8ea7337828b9aa3477261c05c.png"));
        scaleInAnimationAdapter.notifyDataSetChanged();
    }





}