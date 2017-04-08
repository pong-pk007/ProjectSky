package com.example.pongs_000.projectsky.SilkType;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.pongs_000.projectsky.R;
import com.example.pongs_000.projectsky.SilkType.MySQL.SilkT_Downloader;
import com.example.pongs_000.projectsky.urlWebServer;

public class SilkTypeActivity extends AppCompatActivity {

    RecyclerView rvsilkT;
    private static String Server = new urlWebServer().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silk_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarType);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ประเภทเส้นไหม");


        rvsilkT = (RecyclerView) findViewById(R.id.rvsilktype);
        rvsilkT.setHasFixedSize(true);
        rvsilkT.setItemAnimator(new DefaultItemAnimator());
        rvsilkT.setLayoutManager(new LinearLayoutManager(this));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.sw_silktype);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.yellow,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_STATE_TOO_SMALL);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);

        new SilkT_Downloader(SilkTypeActivity.this, Server+"ssp/GET_JSON/getsilktype.php",rvsilkT,swipeRefreshLayout).execute();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new SilkT_Downloader(SilkTypeActivity.this, Server+"ssp/GET_JSON/getsilktype.php",rvsilkT,swipeRefreshLayout).execute();
            }
        });


    }
}
