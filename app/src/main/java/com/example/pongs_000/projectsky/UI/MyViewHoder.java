package com.example.pongs_000.projectsky.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pongs_000.projectsky.R;


/**
 * Created by pongs_000 on 28/9/2559.
 */

public class MyViewHoder extends RecyclerView.ViewHolder implements  View.OnClickListener {


    TextView name;
    TextView price;
    ImageView img;
    ItemClickListener itemClickListener;




    public MyViewHoder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tvdataname);
        price = (TextView) itemView.findViewById(R.id.tvdataprice);
        img = (ImageView) itemView.findViewById(R.id.imgdata);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick();
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }
}
