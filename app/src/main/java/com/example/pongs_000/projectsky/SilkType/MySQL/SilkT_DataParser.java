package com.example.pongs_000.projectsky.SilkType.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pongs_000.projectsky.SilkType.Object.SilkTypeOBJ;
import com.example.pongs_000.projectsky.SilkType.UI.SilkTAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class SilkT_DataParser extends AsyncTask<Void,Void,Boolean> {
        Context c;
    String jsonData;
    RecyclerView rvsilkT;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<SilkTypeOBJ> silkTypeOBJs = new ArrayList<>();

    public SilkT_DataParser(Context c, String jsonData, RecyclerView rvsilkT, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rvsilkT = rvsilkT;
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
            rvsilkT.setAdapter(new SilkTAdapter(c,silkTypeOBJs));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            silkTypeOBJs.clear();
            SilkTypeOBJ SToBJ;

            for (int i=0; i<ja.length(); i++){

                jo=ja.getJSONObject(i);

                int st_id = jo.getInt("sector_id");
                String name_type = jo.getString("name_type");
                String description = jo.getString("description");
                String image = jo.getString("image");

                SToBJ = new SilkTypeOBJ();

                SToBJ.setSilkT_ID(st_id);
                SToBJ.setName_type(name_type);
                SToBJ.setDescrip(description);
                SToBJ.setImage(image);

                silkTypeOBJs.add(SToBJ);

            }
            return true;

        }catch (JSONException e){
        e.printStackTrace();

        }
        return false;
    }
}
