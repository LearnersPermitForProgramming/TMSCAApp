package com.tmsca.sagarwal.tmscaapp.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tmsca.sagarwal.tmscaapp.R;

import java.util.ArrayList;
import java.util.List;

//ItemAdapter

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
    private List<Item> itemsList;
    Intent i;
    public ItemAdapter(List<Item> itemsList){
        this.itemsList = itemsList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, quality;
        public ImageView image;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            quality = (TextView) view.findViewById(R.id.descp);
            image = (ImageView) view.findViewById(R.id.i);
            final Context context = view.getContext();
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Getting the string for recyclerview
                    String recyclerViewTitle = title.getText().toString();
                    if(recyclerViewTitle.equals("Apollo 11")){



                    }
                }
            });

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        WindowManager windowManager = (WindowManager)parent.getContext().getSystemService(Context.WINDOW_SERVICE);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item items = itemsList.get(position);
        holder.title.setText(items.getTitle());
        holder.quality.setText(items.getDescp());
        if(items.url.isEmpty()){
            items.url = "https://via.placeholder.com/350x150";
        }else{
            Picasso.get().load(items.url).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public void setFilter(List<Item> itemList) {
        itemsList = new ArrayList<>();
        itemsList.addAll(itemList);
        notifyDataSetChanged();
    }
}
