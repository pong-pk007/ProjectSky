package com.example.pongs_000.projectsky.SilkData.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pongs_000.projectsky.AnimationUtil;
import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.SilkData.Object.SilkDataOBJ;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkDAdapter extends RecyclerView.Adapter<SilkDHoder> {

    Context c;
    ArrayList<SilkDataOBJ> SDobject;
    private int previousPosition = 0;

    public SilkDAdapter(Context c, ArrayList<SilkDataOBJ> SDobject) {
        this.c = c;
        this.SDobject = SDobject;
    }

    @Override
    public SilkDHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemslikdata, parent, false);
        return new SilkDHoder(v);
    }

    @Override
    public void onBindViewHolder(SilkDHoder holder, int position) {

//        if (position > previousPosition) { // We are scrolling DOWN
//
//            AnimationUtil.animate(holder, true);
//
//        } else { // We are scrolling UP
//
//            AnimationUtil.animate(holder, false);
//
//        }
//        previousPosition = position;


        final SilkDataOBJ SD = SDobject.get(position);


        holder.SDname.setText(SD.getSericulture_sector());
        holder.price.setText(SD.getPrice());

    }

    @Override
    public int getItemCount() {
        return SDobject.size();
    }


}
