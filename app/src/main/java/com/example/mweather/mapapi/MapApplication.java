package com.example.mweather.mapapi;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

public class MapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.GCJ02);
    }
}
