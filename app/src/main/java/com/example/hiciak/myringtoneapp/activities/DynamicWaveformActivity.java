package com.example.hiciak.myringtoneapp.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hiciak.myringtoneapp.R;
import com.example.hiciak.myringtoneapp.custom_views.MyDynamicWaveformView;
import com.example.hiciak.myringtoneapp.parameters.GlobParam;


public class DynamicWaveformActivity extends Activity {

    private LinearLayout mainLinearLayout;
    private MediaPlayer myMediaPlayer;
    private Visualizer myVisualizer;
    private MyDynamicWaveformView myWaveformView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_dynamic_waveform);
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

    private int myMediaPlayerInstantiator() {
        this.myMediaPlayer = MediaPlayer.create(this, R.raw.test_song);
        int audioSessionId = 0;
        audioSessionId = this.myMediaPlayer.getAudioSessionId();
        Log.i(GlobParam.LOG_TAG, "AudioSessionId is: " + audioSessionId);
        return audioSessionId;
    }

    private void myVisualizerInstantiator(int audioSessionId) {

        //Instantiation of Waveform view
        this.myWaveformView = new MyDynamicWaveformView(this);

        int viewHeight = (int) (150 * this.getResources().getDisplayMetrics().density);
        ViewGroup.LayoutParams myWaveformViewParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight);
        myWaveformView.setLayoutParams(myWaveformViewParameters);

        this.mainLinearLayout.addView(myWaveformView);

        //Instantiation of the visualizer
        this.myVisualizer = new Visualizer(audioSessionId);

        this.myVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        Visualizer.OnDataCaptureListener myOnDataCaptureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int i) {
                if(DynamicWaveformActivity.this.myWaveformView != null) {
                   DynamicWaveformActivity.this.myWaveformView.updateVisualitzerView(bytes);
                }
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int i) {

            }
        };
        this.myVisualizer.setDataCaptureListener(myOnDataCaptureListener, Visualizer.getMaxCaptureRate(), true, false);


        this.myVisualizer.setEnabled(true);
    }

    private void myTestMethod() {

        int audioSessionId = myMediaPlayerInstantiator();

        if(audioSessionId != 0) {
            myVisualizerInstantiator(audioSessionId);
            this.myMediaPlayer.start();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        if(this.isFinishing() && this.myMediaPlayer != null) {
            this.myVisualizer.release();
            this.myMediaPlayer.stop();
            this.myMediaPlayer.release();
        }
    }
}
