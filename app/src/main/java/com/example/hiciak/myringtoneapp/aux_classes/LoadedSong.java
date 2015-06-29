package com.example.hiciak.myringtoneapp.aux_classes;

import android.os.Environment;
import android.util.Log;

import com.example.hiciak.myringtoneapp.parameters.GlobParam;

import java.io.File;

/**
 * Created by Michal Krysiak.
 */
public class LoadedSong {

    private File song;

    public LoadedSong(String songsName) {
        this.song = null;
        instantiateTheFileObject(songsName);
    }

    private void instantiateTheFileObject(String songsName) {
        String songsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath() + "/" + songsName + ".mp3";
        try {
            this.song = new File(songsPath);
        } catch(Exception e) {
            Log.e(GlobParam.LOG_TAG, "Error in LoadedSong in instantiateTheFileObject, the error message is: " + e.getMessage());
        }
    }
}
