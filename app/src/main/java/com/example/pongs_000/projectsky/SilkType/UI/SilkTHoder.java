package com.example.pongs_000.projectsky.SilkType.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.UI.ItemClickListener;


/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkTHoder extends RecyclerView.ViewHolder implements  View.OnClickListener {


    TextView STname;
    ImageView imgSilkT;
    ItemClickListener itemClickListener;




    public SilkTHoder(View itemView) {
        super(itemView);
        STname = (TextView) itemView.findViewById(R.id.tvtypename);
        imgSilkT = (ImageView) itemView.findViewById(R.id.imgtype);

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
