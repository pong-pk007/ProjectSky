package com.example.pongs_000.projectsky.SilkData;

import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.SilkData.MySQL.SilkD_Downloader;
import com.example.pongs_000.projectsky.urlWebServer;

public class SilkDataActivity extends AppCompatActivity {

    RecyclerView rv;
    private static String Server = new urlWebServer().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silk_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSilkData);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ข้อมูลเส้นไหม");

        rv = (RecyclerView) findViewById(R.id.rvsilkdata);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this));


        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.sw_silkdata);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.yellow,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_STATE_TOO_SMALL);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);

        new SilkD_Downloader(SilkDataActivity.this, Server+"ssp/GET_JSON/getsilkdata.php",rv,swipeRefreshLayout).execute();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new SilkD_Downloader(SilkDataActivity.this, Server+"ssp/GET_JSON/getsilkdata.php",rv,swipeRefreshLayout).execute();
            }
        });






    }
}
