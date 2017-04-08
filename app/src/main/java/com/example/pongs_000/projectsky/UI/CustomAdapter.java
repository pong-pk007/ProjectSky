package com.example.pongs_000.projectsky.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pongs_000.projectsky.AnimationUtil;
import com.example.pongs_000.projectsky.DataObject.spData;
import com.example.pongs_000.projectsky.Detail;
import com.example.pongs_000.projectsky.R;
import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class CustomAdapter extends RecyclerView.Adapter<MyViewHoder> {

    Context c;
    ArrayList<spData> spDatas;
    private int previousPosition = 0;

    public CustomAdapter(Context c, ArrayList<spData> spDatas){
        this.c = c;
        this.spDatas = spDatas;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_data,parent,false);
        return new MyViewHoder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

//        if(position > previousPosition){ // We are scrolling DOWN
//
//            AnimationUtil.animate(holder, true);
//
//        }else{ // We are scrolling UP
//
//            AnimationUtil.animate(holder, false);
//
//        }
//        previousPosition = position;



        final spData SP = spDatas.get(position);


        holder.name.setText(SP.getOwner());
        holder.price.setText(SP.getPrice()+" ฿");
        PicassoClient.downloadImage(c,SP.getImage(),holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenDetailRestaurantActivity(SP.getSp_id(),SP.getAddress(),SP.getImage(),SP.getLat(),SP.getLongt(),SP.getOwner(),SP.getPrice(),SP.getTelephone(),SP.getName_type());
            }
        });
    }


    @Override
    public int getItemCount() {
        return spDatas.size();
    }



//    public void setFilter (ArrayList<spData> restaurantDatas){
//        restaurantDatas = new ArrayList<>();
//        restaurantDatas.addAll(restaurantDatas);
//        notifyDataSetChanged();
//
//    }

//    int sp_id;
//    String place,owner,telephone,image,address,lat,longt;

    private void OpenDetailRestaurantActivity(int sp_id,String address,String image,String lat,String longt,String owner,String price,String telephone,String name_type){

        Intent i = new Intent(c, Detail.class);

        i.putExtra("SPID_KEY",sp_id);
        i.putExtra("ADDRESS_KEY",address);
        i.putExtra("PRICE_KEY",price);
        i.putExtra("OWNER_KEY",owner);
        i.putExtra("PHONE_KEY",telephone);
        i.putExtra("IMAGE_KEY",image);
        i.putExtra("LAT_KEY" ,lat);
        i.putExtra("LONGT_KEY", longt);
        i.putExtra("NAME_TYPE_KEY" ,name_type);
        c.startActivity(i);
    }

}
