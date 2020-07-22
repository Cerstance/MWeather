package com.example.mweather.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mweather.R;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RelativeLayout rLayoutHua = (RelativeLayout) findViewById(R.id.rlayout_hua);
        RelativeLayout rLayoutShe = (RelativeLayout) findViewById(R.id.rlayout_she);
        RelativeLayout rLayoutAbout = (RelativeLayout) findViewById(R.id.rLayout_about);
        TextView tvShe = (TextView) findViewById(R.id.tv_she_right);
        TextView tvHua = (TextView) findViewById(R.id.tv_hua_right);
        ImageView settingBack = (ImageView) findViewById(R.id.iv_setting_back);
        ImageView sheRight = (ImageView) findViewById(R.id.iv_she_right);
        ImageView huaRight = (ImageView) findViewById(R.id.iv_hua_right);

        rLayoutHua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheRight.setVisibility(View.GONE);
                huaRight.setVisibility(View.VISIBLE);
                tvHua.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                tvShe.setTextColor(R.color.color_a4a4a4);
                WeatherActivity.setUnit("i");
                System.out.println("hua");
            }
        });
        rLayoutShe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheRight.setVisibility(View.VISIBLE);
                huaRight.setVisibility(View.GONE);
                tvHua.setTextColor(getResources().getColor(R.color.color_a4a4a4));
                tvShe.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                WeatherActivity.setUnit("m");
                System.out.println("she");
            }
        });
        settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("back");
                Intent intent = new Intent(SettingActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        rLayoutAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AboutAcitvity.class);
                startActivity(intent);
            }
        });
    }
}
