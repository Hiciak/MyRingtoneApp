package com.example.hiciak.myringtoneapp.waveform_master.activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hiciak.myringtoneapp.R;
import com.example.hiciak.myringtoneapp.parameters.GlobParam;
import com.example.hiciak.myringtoneapp.waveform_master.adapters.MyBaseAdapter;

import java.io.File;

public class ChooseSongActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView lvListOfSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);
        fillListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_choose_song, menu);
        MenuItem menuItem = menu.add(0, GlobParam.BACK_FROM_CHOOSE_SONG, 100, "BACK");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case GlobParam.BACK_FROM_CHOOSE_SONG:
                actionAfterBackFromChooseSong();
                break;
            default:
                GlobParam.makeInfoLog("Error in ChooseSongActivity in onOptionsItemSelected");
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionAfterBackFromChooseSong() {
        this.setResult(RESULT_OK);
        this.finish();
    }

    private void test() {
        File folderWithSongs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File[] listOfFiles = folderWithSongs.listFiles();
        StringBuilder sb = new StringBuilder();
        for(File f : listOfFiles) {
            sb.append(f.getAbsolutePath() + "\n");
        }
        GlobParam.makeInfoLog(sb.toString());
    }

    private void fillListView() {
        this.lvListOfSongs = (ListView) findViewById(R.id.lv_activity_choose_song_list_of_songs);
        MyBaseAdapter myBaseAdapter = null;

        File folderWithSongs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File[] listOfFiles = folderWithSongs.listFiles();

        if(this.lvListOfSongs != null && listOfFiles != null) {
            this.lvListOfSongs.setOnItemClickListener(this);
            myBaseAdapter = new MyBaseAdapter(listOfFiles, this);
            this.lvListOfSongs.setAdapter(myBaseAdapter);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File songFile = (File) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        String songsPath = songFile.getAbsolutePath();
        intent.putExtra(GlobParam.BUNDLE_CONTENT_STRING_SONG_PATH, songsPath);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }
}
