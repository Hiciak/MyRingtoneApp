package com.example.hiciak.myringtoneapp.parameters;

import android.util.Log;

/**
 * Created by hiciak on 29/06/15.
 */
public class GlobParam {

    /*GENERAL PARAMS AND NAMES*/
    public static final String LOG_TAG = "MY_DEBUG";

    /*IDENTIFIERS FOR FRAGMENTS FROM WaveformMasterActivity*/
    public static final String WAVEFORM_VIEW_TAG = "WAVEFORM_VIEW_TAG";

    /*IDENTIFIERS FOR BUNDLE CONTENT FROM WaveformMasterActivty*/
    public static final String BUNDLE_CONTENT_STRING_SONG_PATH = "BUNDLE_CONTENT_STRING_SONG_PATH";

    /*IDENTIFIERS FOR MENU ITEMS FROM WaveformMasterActivity*/
    public static final int CHOOSE_SONG = 0000001;
    public static final int BACK_FROM_CHOOSE_SONG = 0000002;

    public static void makeInfoLog(String textToShowInLog) {
        Log.i(LOG_TAG, textToShowInLog);
    }

}
