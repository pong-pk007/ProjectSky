package com.example.pongs_000.projectsky;

import android.content.Context;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;

import com.example.pongs_000.projectsky.MySQL.Downloader;
import com.example.pongs_000.projectsky.SilkData.SilkDataActivity;
import com.example.pongs_000.projectsky.SilkType.MySQL.SilkT_Downloader;
import com.example.pongs_000.projectsky.SilkType.SilkTypeActivity;

public class ScrollingActivity extends AppCompatActivity {

    RecyclerView rv;
    private static String Server = new urlWebServer().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ยินดีตอนรับสู่ แอพพลิเคชั่นแหล่งผลิตหม่อนไหม จ.ศรีสะเกษ");

        findViewById(R.id.card3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this, Contact.class));
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvtype);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.sw_type);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.yellow,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_STATE_TOO_SMALL);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);


        new SilkT_Downloader(ScrollingActivity.this, Server+"ssp/GET_JSON/getsilktype.php",rv,swipeRefreshLayout).execute();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new SilkT_Downloader(ScrollingActivity.this, Server+"ssp/GET_JSON/getsilktype.php",rv,swipeRefreshLayout).execute();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
