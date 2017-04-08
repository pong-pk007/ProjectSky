package com.example.pongs_000.projectsky.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pongs_000.projectsky.DataObject.spData;
import com.example.pongs_000.projectsky.UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class DataParser extends AsyncTask<Void,Void,Boolean> {
        Context c;
    String jsonData;
    RecyclerView rvres;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<spData> spDatas=new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView rvres, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rvres = rvres;
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
            rvres.setAdapter(new CustomAdapter(c,spDatas));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            spDatas.clear();
            spData SpData;

            for (int i=0; i<ja.length(); i++){

                jo=ja.getJSONObject(i);

                String price = jo.getString("price");
                String owner = jo.getString("owner");
                String telephone = jo.getString("telephone");
                String image = jo.getString("image");
                String address = jo.getString("address");
                String lat = jo.getString("latitude");
                String longt = jo.getString("longitude");
                String name_type = jo.getString("name_type");
                String descrip = jo.getString("description");

                SpData = new spData();

                SpData.setPrice(price);
                SpData.setOwner(owner);
                SpData.setTelephone(telephone);
                SpData.setImage(image);
                SpData.setAddress(address);
                SpData.setLat(lat);
                SpData.setLongt(longt);
                SpData.setName_type(name_type);
                SpData.setDescription(descrip);
                spDatas.add(SpData);

            }
            return true;

        }catch (JSONException e){
        e.printStackTrace();

        }
        return false;
    }
}
