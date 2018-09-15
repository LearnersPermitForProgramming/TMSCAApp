package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmsca.sagarwal.tmscaapp.R;
import com.tmsca.sagarwal.tmscaapp.Recycler.Item;
import com.tmsca.sagarwal.tmscaapp.Recycler.ItemAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFragment extends Fragment {

    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    ScaleInAnimationAdapter scaleInAnimationAdapter;


    //onCreateView function
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.resources, container, false);

        recyclerView = rootView.findViewById(R.id.rec_view);

        getActivity().setTitle("Resources");

        itemAdapter = new ItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        // Animation Adapter stuff
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(itemAdapter);
        scaleInAnimationAdapter.setDuration(500);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        loadItems();

        return rootView;
    }


    void loadItems(){
        itemList.add(new Item("Dates", "Here you will find upcoming meets and qualifying test dates!", ""));
        itemList.add(new Item("Test Links/Study Links",
                "Find links to tests and studying links that can help you improve at taking tests for TMSCA!",
                ""));
        scaleInAnimationAdapter.notifyDataSetChanged();





    }
}
