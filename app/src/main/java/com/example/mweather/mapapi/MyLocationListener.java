package com.example.mweather.mapapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.mweather.AirBean;
import com.example.mweather.bean.CityBean;
import com.example.mweather.view.WeatherActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyLocationListener extends BDAbstractLocationListener {
    private static String cityUrl;
    private final static String TAG = "TAG";
    private Handler handler4;

    public static String getCity() {
        return city;
    }

    private static String city;
    @Override
    public void onReceiveLocation(BDLocation location){
        handler4 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                WeatherActivity.setLocation(msg.getData().getString("locId"));
                WeatherActivity.setTvLocation(city);
            }
        };

        try{
            System.out.println(location.getCity());
            while (location.getCity() == null){
                city = new String(location.getCity().getBytes(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cityUrl = "https://geoapi.heweather.net/v2/city/lookup?location="+
                city + "&key=" + WeatherActivity.getKEY();

        final Request cityRequest = new Request.Builder()
                .url(cityUrl)
                .get()//默认就是GET请求，可以不写
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call cityCall = okHttpClient.newCall(cityRequest);
        cityCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println(result);
                CityBean cityBean = new Gson().fromJson(result, CityBean.class);
                List<CityBean.Location> locList = cityBean.getLocation();
                String locId = locList.get(0).getId();

                Bundle bundle = new Bundle();
                bundle.putString("locId", locId);

                Message msg = new Message();
                msg.setData(bundle);

                handler4.sendMessage(msg);
            }
        });
    }
}
