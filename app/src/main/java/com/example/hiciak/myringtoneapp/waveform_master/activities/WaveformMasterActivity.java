package com.example.hiciak.myringtoneapp.waveform_master.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hiciak.myringtoneapp.R;
import com.example.hiciak.myringtoneapp.parameters.GlobParam;
import com.example.hiciak.myringtoneapp.waveform_master.fragments.WaveformFragment;

public class WaveformMasterActivity extends ActionBarActivity {

    private static final int REQ_CODE = 55;
    private static String songsPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveform_master);
        this.songsPath = null;
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new CustomWaveformFragment())
//                    .commit();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_waveform_master, menu);
        MenuItem menuItem = menu.add(0, GlobParam.CHOOSE_SONG, 101, "Choose song");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case GlobParam.CHOOSE_SONG:
                actionAfterChooseSongSelected();
                break;
            default:
                GlobParam.makeInfoLog("There is an error in onOptionsItemSelected!");
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionAfterChooseSongSelected() {
        Intent intent = new Intent(this, ChooseSongActivity.class);
        this.startActivityForResult(intent, this.REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == this.REQ_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    this.songsPath = data.getStringExtra(GlobParam.BUNDLE_CONTENT_STRING_SONG_PATH);
                    showFragmentWithWaveForm();
                }
            }
        }
    }

    private void showFragmentWithWaveForm() {

        android.support.v4.app.FragmentManager fm = this.getSupportFragmentManager();
        CustomWaveformFragment customWaveformFragment = (CustomWaveformFragment) fm.findFragmentByTag(GlobParam.WAVEFORM_VIEW_TAG);

        if(customWaveformFragment == null) {
            customWaveformFragment = new CustomWaveformFragment();
        }

        if(!customWaveformFragment.isAdded()) {
            fm.beginTransaction().add(R.id.container, customWaveformFragment, GlobParam.WAVEFORM_VIEW_TAG).commit();
        } else {
            //Here I will try to remove and then add a fragment in order to change the song
            if(!this.songsPath.equalsIgnoreCase(customWaveformFragment.getFileName())) {
                customWaveformFragment = new CustomWaveformFragment();
                fm.beginTransaction().remove(customWaveformFragment).add(R.id.container, customWaveformFragment, GlobParam.WAVEFORM_VIEW_TAG).commit();
            }
        }
    }

    public static class CustomWaveformFragment extends WaveformFragment {

//        String songsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath() + "/" + "test_song" + ".mp3";

        @Override
        protected String getFileName() {
            return songsPath;
        }
    }
}
