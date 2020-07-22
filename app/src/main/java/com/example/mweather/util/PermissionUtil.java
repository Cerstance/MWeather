package com.example.mweather.util;

import android.Manifest;
import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class PermissionUtil {
    public static void getPermissions(Activity activity){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .requestEach(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        )
                .subscribe(permission -> { // will emit 2 Permission objects
                if (permission.granted) {
                    // `permission.name` is granted !
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // Denied permission without ask never again
                } else {
                    // Denied permission with ask never again
                    // Need to go to the settings
                }
        });
    }
}
