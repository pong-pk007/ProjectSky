package com.example.pongs_000.projectsky.SilkData.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pongs_000.projectsky.SilkData.Object.SilkDataOBJ;
import com.example.pongs_000.projectsky.SilkData.UI.SilkDAdapter;
import com.example.pongs_000.projectsky.SilkType.Object.SilkTypeOBJ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkD_DataParser extends AsyncTask<Void,Void,Boolean> {
        Context c;
    String jsonData;
    RecyclerView rvsilkD;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<SilkDataOBJ> silkDataOBJs = new ArrayList<>();

    public SilkD_DataParser(Context c, String jsonData, RecyclerView rvsilkD, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rvsilkD = rvsilkD;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        swipeRefreshLayout.setRefreshing(false);

        if (isParsed){
            //bind
            rvsilkD.setAdapter(new SilkDAdapter(c,silkDataOBJs));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            silkDataOBJs.clear();

            SilkDataOBJ SDoBJ;

            for (int i=0; i<ja.length(); i++){

                jo=ja.getJSONObject(i);

                int seri_id = jo.getInt("seri_id");
                String sericulture_sector = jo.getString("sericulture_sector");
                String price = jo.getString("price");

                SDoBJ = new SilkDataOBJ();

                SDoBJ.setSilk_data_id(seri_id);
                SDoBJ.setSericulture_sector(sericulture_sector);
                SDoBJ.setPrice(price);

                silkDataOBJs.add(SDoBJ);

            }
            return true;

        }catch (JSONException e){
        e.printStackTrace();

        }
        return false;
    }
}
