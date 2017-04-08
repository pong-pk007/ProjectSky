package com.example.pongs_000.projectsky.SilkData.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.UI.ItemClickListener;


/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkDHoder extends RecyclerView.ViewHolder{


    TextView SDname,price;




    public SilkDHoder(View itemView) {
        super(itemView);

        price = (TextView) itemView.findViewById(R.id.tvsilkprice);
        SDname = (TextView) itemView.findViewById(R.id.tvsilkname);

//        itemView.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View view) {
//        this.itemClickListener.onItemClick();
//    }
//
//
//    public void setItemClickListener(ItemClickListener itemClickListener){
//        this.itemClickListener=itemClickListener;
//
//    }
}
