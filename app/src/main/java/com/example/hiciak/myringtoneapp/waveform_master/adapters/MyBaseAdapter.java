package com.example.hiciak.myringtoneapp.waveform_master.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hiciak.myringtoneapp.R;

import java.io.File;

/**
 * Created by hiciak on 23/06/15.
 */
public class MyBaseAdapter extends BaseAdapter {

    private File[] listOfSongs;
    private Activity activity;

    public MyBaseAdapter(File[] listOfSongs, Activity activity) {
        this.listOfSongs = listOfSongs;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.listOfSongs.length;
    }

    @Override
    public Object getItem(int i) {
        return this.listOfSongs[i];
    }

    @Override
    public long getItemId(int i) {
        return this.listOfSongs[i].getUsableSpace();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();

        if(view == null) {
            view = layoutInflater.inflate(R.layout.adapter_item_file_representation, viewGroup, false);
        }

//        if(this.listOfItemsToShow.get(i).isSelected()) {
//            view.setBackgroundColor(Color.RED);
//        }
//        else {
//            view.setBackgroundColor(Color.TRANSPARENT);
//        }

        TextView tvFileName = (TextView) view.findViewById(R.id.tv_adapter_item_file_representation_file_name);
        tvFileName.setText(this.listOfSongs[i].getName());

        return view;
    }
}
