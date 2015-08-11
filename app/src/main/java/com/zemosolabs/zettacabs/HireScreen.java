package com.zemosolabs.zettacabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class HireScreen extends FragmentActivity implements LocationListener, View.OnClickListener {
    private final double LATITUDE = 17.422, LONGITUDE = 78.382;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double longitude = LONGITUDE,latitude=LATITUDE;
    private RelativeLayout car_select,bus_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_screen);
        LocationManager locMgr = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        car_select = (RelativeLayout)findViewById(R.id.car_select_layout_id);
        car_select.setOnClickListener(this);
        bus_select = (RelativeLayout)findViewById(R.id.bus_select_layout_id);
        bus_select.setOnClickListener(this);
        getLocation();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
        setUpMapIfNeeded();
    }

    private void getLocation() {

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),14.0f));
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        longitude = LONGITUDE;
        latitude = LATITUDE;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.car_select_layout_id:
                ((TextView)findViewById(R.id.title_navbar)).setText("HIRE CAR");
                break;
            case R.id.bus_select_layout_id:
                ((TextView)findViewById(R.id.title_navbar)).setText("HIRE VAN");
                break;
            default:
                break;
        }
        if(v.getId()==R.id.car_select_layout_id||v.getId()==R.id.bus_select_layout_id){
            Drawable temp = car_select.getBackground();
            car_select.setBackground(bus_select.getBackground());
            bus_select.setBackground(temp);
        }
    }
}
