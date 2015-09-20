package com.mnjy.hackmit;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static android.location.LocationManager.*;

public class MapsActivity extends FragmentActivity implements LocationListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager locMan;
    private Marker userMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    private void updatePlaces() {
        locMan = (LocationManager)(getSystemService(LOCATION_SERVICE));
        Location lastLoc = null;
        try{
            lastLoc = locMan.getLastKnownLocation(GPS_PROVIDER);
        }
        catch (Exception e) {
        }
        double lat = lastLoc.getLatitude();
        double lng = lastLoc.getLongitude();
        LatLng lastLatLng = new LatLng(lat, lng);
        userMarker = mMap.addMarker(new MarkerOptions().position(lastLatLng)
                .position(lastLatLng)
                .title("You are here")
//                .icon(BitmapDescriptorFactory.fromResource(userIcon))
                .snippet("Your last recorded location"));
        if (userMarker != null) userMarker.remove();
        mMap.animateCamera(CameraUpdateFactory.newLatLng(lastLatLng), 3000, null);
    }

    private void StartLocators(){
        List<String> locators = locMan.getProviders(true);
        for (String aprovider : locators){
            if(aprovider.equals(LocationManager.GPS_PROVIDER)){
                try {
                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
                }
                catch (Exception e){
                }
            }

            if (aprovider.equals(LocationManager.NETWORK_PROVIDER)) {
                try {
                    locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, this);
                }
                catch (Exception e){
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        StartLocators();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
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
//  mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.229, -80.424), 14.8f));
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            locMan.removeUpdates(this);
        }
        catch(Exception e){
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
