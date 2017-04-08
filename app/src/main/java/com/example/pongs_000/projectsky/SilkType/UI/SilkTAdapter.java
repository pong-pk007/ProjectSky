package com.example.pongs_000.projectsky.SilkType.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pongs_000.projectsky.AnimationUtil;
import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.SilkType.Object.SilkTypeOBJ;
import com.example.pongs_000.projectsky.SilkType.SilktDetail;
import com.example.pongs_000.projectsky.UI.ItemClickListener;
import com.example.pongs_000.projectsky.UI.PicassoClient;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkTAdapter extends RecyclerView.Adapter<SilkTHoder> {

    Context c;
    ArrayList<SilkTypeOBJ> STobject;
    private int previousPosition = 0;

    public SilkTAdapter(Context c, ArrayList<SilkTypeOBJ> STobject) {
        this.c = c;
        this.STobject = STobject;
    }

    @Override
    public SilkTHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_type, parent, false);
        return new SilkTHoder(v);
    }

    @Override
    public void onBindViewHolder(SilkTHoder holder, int position) {

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


        final SilkTypeOBJ ST = STobject.get(position);


        holder.STname.setText(ST.getName_type());
        PicassoClient.downloadImage(c, ST.getImage(), holder.imgSilkT);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenDetailSilkTActivity(ST.getName_type(),ST.getDescrip(),ST.getImage(),ST.getSilkT_ID());
            }
        });
    }


    @Override
    public int getItemCount() {
        return STobject.size();
    }

//
//    public void setFilter(ArrayList<SilkTypeOBJ> silkTypeOBJs) {
//        silkTypeOBJs = new ArrayList<>();
//        silkTypeOBJs.addAll(silkTypeOBJs);
//        notifyDataSetChanged();
//
//    }



    private void OpenDetailSilkTActivity(String name,String descrip,String image, int id){

        Intent i = new Intent(c, SilktDetail.class);

        i.putExtra("ID_KEY",id);
        i.putExtra("NAMETYPT_KEY",name);
        i.putExtra("DESCRIP_KEY",descrip);
        i.putExtra("IMAGE_KEY",image);
        c.startActivity(i);
    }

}
