package com.example.konrad.konradstraszewski;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Konrad on 2015-01-06.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    SplashScreen splashscreen = new SplashScreen();
    public String[] urllist = splashscreen.UrlList;
    public String[] desclist = splashscreen.DescList;
    public String[] titlelist = splashscreen.TitleList;
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return urllist.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        TextView txtDesc;
        TextView txtTitle;
        ImageView imgIcon;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {

            ImageLoader imageLoader= new ImageLoader(mContext);
            grid = inflater.inflate(R.layout.layout, null);

            txtTitle = (TextView) grid.findViewById(R.id.textTitle);
            txtDesc = (TextView) grid.findViewById(R.id.textDesc);
            imgIcon = (ImageView) grid.findViewById(R.id.imgIcon);

            imgIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            grid.setPadding(8, 8, 8, 8);
            imageLoader.DisplayImage(urllist[position], 30, imgIcon);

            txtDesc.setText(desclist[position]);
            txtTitle.setText(titlelist[position]);

        } else {
            grid = convertView;
        }

        return grid;
    }



}