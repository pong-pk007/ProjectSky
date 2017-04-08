package com.example.pongs_000.projectsky.SilkType;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pongs_000.projectsky.MySQL.Downloader;
import com.example.pongs_000.projectsky.PlaceActivity;
import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.UI.PicassoClient;
import com.example.pongs_000.projectsky.urlWebServer;

public class SilktDetail extends AppCompatActivity {

    ImageView imgSilkTD;
    TextView tvname,tvdes,tvlabel;

    RecyclerView rv;
    private static String Server = new urlWebServer().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silkt_detail);

        getSupportActionBar().setTitle("รายละเอียด");


        rv = (RecyclerView) findViewById(R.id.rvdata);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imgSilkTD = (ImageView) findViewById(R.id.ivsilkT);
        tvname = (TextView) findViewById(R.id.tvDname_type);
        tvdes = (TextView) findViewById(R.id.tvdescrip);
        tvlabel = (TextView) findViewById(R.id.tvtypelabel);

        String name,des,img,price;
        final int id;
        final Intent i = this.getIntent();

        id = i.getExtras().getInt("ID_KEY");
        name = i.getExtras().getString("NAMETYPT_KEY");
        des = i.getExtras().getString("DESCRIP_KEY");
        img = i.getExtras().getString("IMAGE_KEY");

        tvname.setText(name);
        tvdes.setText("\t"+des);
        tvlabel.setText("แหล่งผลิตที่จำแนกตามประเภท "+name);
        PicassoClient.downloadImage(this,img,imgSilkTD);


        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.sw_data);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.yellow,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_STATE_TOO_SMALL);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);


        new Downloader(SilktDetail.this, Server+"ssp/GET_JSON/getsilkdata.php?id="+id,rv,swipeRefreshLayout).execute();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Downloader(SilktDetail.this, Server+"ssp/GET_JSON/getsilkdata.php?id="+id,rv,swipeRefreshLayout).execute();
            }
        });

    }
}
