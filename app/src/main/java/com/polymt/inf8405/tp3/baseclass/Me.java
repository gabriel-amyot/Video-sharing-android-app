package com.polymt.inf8405.tp3.baseclass;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class Me extends User {
    private static Me m_me;
    private Location currentLocation;
    private Invokable invokeLocation;
    public static Me getMe(){
        return m_me;
    }

    public static void setMe(String uId){
        m_me = new Me(uId);
    }
    private Me(String uId){
        super(uId);
        loadFriend();
        loadVideos();
        loadGps();
    }

    private void loadVideos() {
        VideoManager.getInstance().loadMyVideo();
    }

    private void loadFriend() {
    }

    public boolean addFriend(String email){
        //TODO add the friend
        if(UserManager.getIntance().addFriend(uniqueId,email)){//add successfull
            loadFriend();
            return true;
        }
        return false;
    }
    public boolean removeFriend(String friendUniqueId){
        UserManager.getIntance().removeFriend(uniqueId,friendUniqueId);
        loadFriend();
        return true;
    }

    public void setInvokeLocationFunc(Invokable inv){
        invokeLocation = inv;
        inv.invoke();
    }
    private void updateLocation(Location location){
        currentLocation = location;
        if(invokeLocation!=null){
            invokeLocation.invoke();
        }
    }
    private void loadGps(){

        int answerRequest=0;
        LocationManager locationManager;
        if (ContextHolder.getMainContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextHolder.getMainContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)ContextHolder.getMainContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions((Activity)ContextHolder.getMainContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        answerRequest);
            }
        }else{
            // Get the LocationManager object from the System Service LOCATION_SERVICE
            locationManager = (LocationManager) ContextHolder.getMainContext().getSystemService(Context.LOCATION_SERVICE);
            // Create a criteria object needed to retrieve the provider
            Criteria criteria = new Criteria();
            // Get the name of the best available provider
            String provider = locationManager.getBestProvider(criteria, true);
            // We can use the provider immediately to get the last known location
            Location location = locationManager.getLastKnownLocation(provider);

            updateLocation(location);
            locationManager.requestLocationUpdates(provider, 20000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    updateLocation(location);
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
            });
        }
    }

    public Location getLocation(){
        return currentLocation;
    }

    public List<User> getFriend(){
        if(friends!=null){
            List<User> a = new ArrayList<>();
            for(String uiq : friends.keySet()){
                a.add(new User(uiq));
            }return a;
        }else{
            return new ArrayList<>();
        }
    }



}
