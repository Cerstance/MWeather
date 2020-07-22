package com.example.mweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mweather.R;
import com.example.mweather.data.ForecastItem;
import com.example.mweather.util.IconUtil;

import java.util.List;

public class ForecastAdapter extends ArrayAdapter<ForecastItem> {
    private int newResourceId;
    public ForecastAdapter(Context context, int resourceId, List<ForecastItem> forecastList){
        super(context, resourceId, forecastList);
        newResourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ForecastItem forecastItem = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);

        TextView weekDay = (TextView) view.findViewById(R.id.tv_week);
        TextView maxTemp = (TextView) view.findViewById(R.id.tv_max);
        TextView minTemp = (TextView) view.findViewById(R.id.tv_min);

        ImageView ivDay = (ImageView) view.findViewById(R.id.iv_day);
        ImageView ivNight = (ImageView) view.findViewById(R.id.iv_night);

        weekDay.setText (forecastItem.getTvWeekDay());
        maxTemp.setText (forecastItem.getTvMax());
        minTemp.setText (forecastItem.getTvMin());

        ivDay.setImageResource(IconUtil.getDayIconDark(forecastItem.getIvDay()));
        ivNight.setImageResource(IconUtil.getNightIconDark(forecastItem.getIvNight()));

        return view;
    }
}
