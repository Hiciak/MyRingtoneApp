package com.example.hiciak.myringtoneapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hiciak.myringtoneapp.custom_views.MyWaveformView;
import com.example.hiciak.myringtoneapp.parameters.GlobParam;

import java.io.IOException;


public class MainActivity extends Activity {

    LinearLayout mainLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.mainLinearLayout = (LinearLayout) findViewById(R.id.ll_main_container);
        myTestMethod();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void myTestMethod() {
//        Visualizer visualizer = new Visualizer();
//        MediaPlayer mp = MediaPlayer.create(this, R.raw.test_song);
//        mp.start();

        MyWaveformView myWaveformView = new MyWaveformView(this);

        int viewHeight = (int) (150 * this.getResources().getDisplayMetrics().density);
        ViewGroup.LayoutParams myWaveformViewParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight);
        myWaveformView.setLayoutParams(myWaveformViewParameters);

        this.mainLinearLayout.addView(myWaveformView);
    }
}
