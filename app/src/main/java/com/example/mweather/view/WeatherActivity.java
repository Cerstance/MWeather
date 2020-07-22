package com.example.mweather.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.mweather.AirBean;
import com.example.mweather.adapter.ForecastAdapter;
import com.example.mweather.R;
import com.example.mweather.bean.CityBean;
import com.example.mweather.bean.DailyBean;
import com.example.mweather.bean.NowBean;
import com.example.mweather.data.ForecastItem;
import com.example.mweather.mapapi.MyLocationListener;
import com.example.mweather.util.DateUtil;
import com.example.mweather.util.PermissionUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private final static String TAG = "TAG";
    private static String location = "101060101";

    public static void setUnit(String unit) {
        WeatherActivity.unit = unit;
    }

    private static String unit = "m";

    public static String getKEY() {
        return KEY;
    }

    private final static String KEY = "d4c789e423a84da5bcf2741197429198";

    public static LocationClient mLocationClient = null;
    private static MyLocationListener myListener = new MyLocationListener();
    
    private static TextView tvLocation;
    public static void setTvLocation(String location) {
        WeatherActivity.tvLocation.setText(location);
    }

    private static Handler handler1;
    private static Handler handler2;
    private static Handler handler3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        PermissionUtil.getPermissions(this);

        tvLocation = (TextView) findViewById(R.id.tv_location);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);

        class Listener implements SwipeRefreshLayout.OnRefreshListener{
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                startLocation();
                weatherInit();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
        swipeRefreshLayout.setOnRefreshListener(new Listener());

        ImageView addIv = (ImageView) findViewById(R.id.iv_add_city);
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,
                        AddActivity.class);
                startActivity(intent);
            }
        });

        ImageView setIv = (ImageView) findViewById(R.id.iv_set);
        setIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,
                        SettingActivity.class);
                startActivity(intent);
            }
        });

        TextView todayTmp = (TextView) findViewById(R.id.textView_today_tmp);
        TextView nowText = (TextView) findViewById(R.id.textView_now_text);
        TextView maxTmp = (TextView) findViewById(R.id.textView_max_tmp);
        TextView aqi = (TextView) findViewById(R.id.textView_aqi);

        ListView listView = (ListView) findViewById(R.id.list_view);

        TextView rain = (TextView) findViewById(R.id.tv_today_rain);
        TextView humidity = (TextView) findViewById(R.id.tv_today_hum);
        TextView pressure = (TextView) findViewById(R.id.tv_today_pressure);
        TextView vis = (TextView) findViewById(R.id.tv_today_visible);
        TextView windScale = (TextView) findViewById(R.id.tv_wind_sc);

        TextView pm2p5 = (TextView) findViewById(R.id.tv_today_pm25);
        TextView pm10 = (TextView) findViewById(R.id.tv_today_pm10);
        TextView so2 = (TextView) findViewById(R.id.tv_today_so2);
        TextView no2 = (TextView) findViewById(R.id.tv_today_no2);
        TextView co = (TextView) findViewById(R.id.tv_today_co);
        TextView o3 = (TextView) findViewById(R.id.tv_today_o3);

        handler1 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                todayTmp.setText(msg.getData().getString("temp") + "°");
                nowText.setText(msg.getData().getString("text"));

                rain.setText(msg.getData().getString("rain") + "mm");
                humidity.setText(msg.getData().getString("humidity") + "%");
                pressure.setText(msg.getData().getString("pressure") + "hPa");
                vis.setText(msg.getData().getString("vis") + "km");
                windScale.setText(msg.getData().getString("windScale") + "级");
            }
        };
        handler2 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                maxTmp.setText(msg.getData().getString("maxTemp0") + "°" + " / " +
                        msg.getData().getString("minTemp0") + "°");

                ArrayList<ForecastItem> list = new ArrayList<ForecastItem>();
                for(int i = 0;i < 7;i ++){
                    ForecastItem forecastItem = new ForecastItem();
                    forecastItem.setTvMax(msg.getData().getString("maxTemp" + String.valueOf(i)) + "°");
                    forecastItem.setTvMin(msg.getData().getString("minTemp" + String.valueOf(i)) + "°");
                    forecastItem.setTvWeekDay(msg.getData().getString("weekDay" + String.valueOf(i)));
                    forecastItem.setIvDay(msg.getData().getString("iconDay" + String.valueOf(i)));
                    forecastItem.setIvNight(msg.getData().getString("iconDay" + String.valueOf(i)));
                    if(i == 0){
                        forecastItem.setTvWeekDay("今天");
                    }
                    list.add(forecastItem);
                }
//                System.out.println(list.get(1).getTvWeekDay());
                ForecastAdapter forecastAdapter =
                        new ForecastAdapter(WeatherActivity.this, R.layout.item_forecast, list);
                listView.setAdapter(forecastAdapter);

//                maxTmp.setText(msg.getData().getString("week"));
            }
        };
        handler3 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                aqi.setText("空气 " + msg.getData().getString("category"));

                pm2p5.setText(msg.getData().getString("pm2p5"));
                pm10.setText(msg.getData().getString("pm10"));
                so2.setText(msg.getData().getString("so2"));
                no2.setText(msg.getData().getString("no2"));
                co.setText(msg.getData().getString("co"));
                o3.setText(msg.getData().getString("o3"));
            }
        };

//        startLocation();
//        weatherInit();
    }

    public static void setLocation(String location) {
        WeatherActivity.location = location;
    }

    public void startLocation(){
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        option.setNeedNewVersionRgc(true);
        option.setIsNeedAddress(true);

        mLocationClient = new LocationClient(WeatherActivity.this);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);

        mLocationClient.start();
    }

    public void weatherInit(){
        OkHttpClient okHttpClient = new OkHttpClient();
        String nowUrl = "https://devapi.heweather.net/v7/weather/now?location=" + location +"&key="
         + KEY;
//        System.out.println(KEY);
        final Request nowRequest = new Request.Builder()
                .url(nowUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call nowCall = okHttpClient.newCall(nowRequest);
        nowCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());
                String result = response.body().string();
//                System.out.println(result);
                NowBean nowBean = new Gson().fromJson(result, NowBean.class);
                NowBean.Now now = nowBean.getNow();
                String temp = now.getTemp();
                String text = now.getText();

                String rain = now.getPrecip();
                String humidity = now.getHumidity();
                String pressure = now.getPressure();
                String vis = now.getVis();
                String windScale = now.getWindScale();

                Bundle bundle = new Bundle();
                bundle.putString("temp", temp);
                bundle.putString("text", text);

                bundle.putString("rain", rain);
                bundle.putString("humidity", humidity);
                bundle.putString("pressure", pressure);
                bundle.putString("vis", vis);
                bundle.putString("windScale", windScale);

                Message msg = new Message();
                msg.setData(bundle);
                handler1.sendMessage(msg);
            }
        });

        String dailyUrl = "https://devapi.heweather.net/v7/weather/7d?location=" + location +"&key="
                + KEY;

        final Request dailyRequest = new Request.Builder()
                .url(dailyUrl)
                .get()//默认就是GET请求，可以不写
                .build();
//        OkHttpClient okHttpClient = new OkHttpClient();
        Call dailyCall = okHttpClient.newCall(dailyRequest);
        dailyCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());

                String result = response.body().string();
//                System.out.println(result);

                DailyBean dailyBean = new Gson().fromJson(result, DailyBean.class);
                ArrayList<DailyBean.Daily> daily = (ArrayList<DailyBean.Daily>) dailyBean.getDaily();
                Bundle bundle = new Bundle();
                for(int i = 0;i < daily.size();i ++){
                    String maxTemp = daily.get(i).getTempMax();
                    String minTemp = daily.get(i).getTempMin();
                    String weekDay = DateUtil.getWeek(daily.get(i).getFxDate());
                    String iconDay = daily.get(i).getIconDay();
                    String iconNight = daily.get(i).getIconNight();
                    bundle.putString("maxTemp" + String.valueOf(i), maxTemp);
                    bundle.putString("minTemp" + String.valueOf(i), minTemp);
                    bundle.putString("weekDay" + String.valueOf(i), weekDay);
                    bundle.putString("iconDay" + String.valueOf(i), iconDay);
                    bundle.putString("iconNight" + String.valueOf(i), iconNight);
                }
                Message msg = new Message();
                msg.setData(bundle);
                handler2.sendMessage(msg);
            }
        });
        String airUrl = "https://devapi.heweather.net/v7/air/now?location="+ location + "&key="
                + KEY;

        final Request airRequest = new Request.Builder()
                .url(airUrl)
                .get()//默认就是GET请求，可以不写
                .build();
//        OkHttpClient okHttpClient = new OkHttpClient();
        Call airCall = okHttpClient.newCall(airRequest);
        airCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());

                String result = response.body().string();

                AirBean airBean = new Gson().fromJson(result, AirBean.class);
                AirBean.Now now = airBean.getNow();

                String category = now.getCategory();

                String pm2p5 = now.getPm2p5();
                String pm10 = now.getPm10();
                String so2 = now.getSo2();
                String no2 = now.getNo2();
                String co = now.getCo();
                String o3 = now.getO3();

                Bundle bundle = new Bundle();
                bundle.putString("category", category);

                bundle.putString("pm2p5", pm2p5);
                bundle.putString("pm10", pm10);
                bundle.putString("so2", so2);
                bundle.putString("no2", no2);
                bundle.putString("co", co);
                bundle.putString("o3", o3);

                Message msg = new Message();
                msg.setData(bundle);

                handler3.sendMessage(msg);
            }
        });
    }
}
