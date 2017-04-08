package com.example.pongs_000.projectsky;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pongs_000.projectsky.UI.PicassoClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detail extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    TextView tvplace,tvaddress,tvtelephone,tvlatlong, name_type;
    ImageView ivSPdetail;
//    ImageButton btdetail;
    String price,owner,telephone,image,address,latitude,longitude,nameType;

    Toolbar toolbar;

    private GoogleMap mMap;
    private UiSettings mUiSettings;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        toolbar = (Toolbar) findViewById(R.id.toolbardetail);
        toolbar.setLogo(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        int sp_id;
        final Intent i = this.getIntent();
        sp_id = i.getExtras().getInt("SPID_KEY");
        price = i.getExtras().getString("PRICE_KEY");
        address = i.getExtras().getString("ADDRESS_KEY");
        owner = i.getExtras().getString("OWNER_KEY");
        telephone = i.getExtras().getString("PHONE_KEY");
        image = i.getExtras().getString("IMAGE_KEY");
        latitude = i.getExtras().getString("LAT_KEY");
        longitude = i.getExtras().getString("LONGT_KEY");
        nameType = i.getExtras().getString("NAME_TYPE_KEY");

        toolbar.setTitle("  สวน "+owner);

//        getSupportActionBar().setTitle(owner);

//        TextView tvplace,tvaddress,tvowner,tvtelephone,tvlatlong;
//        ImageView ivSPdetail;

        //find view

        tvplace = (TextView) findViewById(R.id.placedetail);
        tvaddress = (TextView) findViewById(R.id.addressdetail);
        tvtelephone = (TextView) findViewById(R.id.telephonedetail);
        tvlatlong = (TextView) findViewById(R.id.latlongdetail);
//        btdetail = (ImageButton) findViewById(R.id.btplacedetail);
        ivSPdetail = (ImageView) findViewById(R.id.imgspdetail);
        name_type = (TextView) findViewById(R.id.name_type);

        tvplace.setText(owner);
        tvaddress.setText("ที่อยู่ : "+address);
        tvtelephone.setText("โทร : "+telephone);
        tvlatlong.setText("ตำแหน่ง : "+latitude+longitude);
        name_type.setText("ประเภท : "+nameType);
        PicassoClient.downloadImage(this,image,ivSPdetail);



//        btdetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + owner + ")";
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
//                intent.putExtra("latitude",latitude);
//                intent.putExtra("longitude",longitude);
//                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//                startActivity(intent);
//
//            }
//        });


    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + owner + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(owner).snippet(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        marker.showInfoWindow();
        mMap.setOnInfoWindowClickListener(this);

    }
}
