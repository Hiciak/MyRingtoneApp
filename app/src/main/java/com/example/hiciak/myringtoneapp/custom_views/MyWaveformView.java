package com.example.hiciak.myringtoneapp.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.example.hiciak.myringtoneapp.parameters.GlobParam;

/**
 * Created by hiciak on 29/06/15.
 */
public class MyWaveformView extends View {

    private Paint myPaint;
    private Rect paintingContainer;
    private byte[] mBytes;
    private float[] mPoints;

    public MyWaveformView(Context context) {
        super(context);
        init();
    }

    private void init() {
        //Instantiation of the paint object
        this.myPaint = new Paint();
        this.myPaint.setStrokeWidth(1f);
        this.myPaint.setAntiAlias(true);
        this.myPaint.setColor(Color.RED);

        //Instantiation of paintingContainer
        this.paintingContainer = new Rect();
        this.paintingContainer.set(0, 0, this.getWidth(), this.getHeight());
    }

    public void updateVisualitzerView(byte[] bytes) {
        this.mBytes = bytes;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.mBytes != null) {
            if(this.mPoints == null || this.mPoints.length < this.mBytes.length * 4) {
                this.mPoints = new float[this.mBytes.length * 4];
            } else {
                Log.i(GlobParam.LOG_TAG, "this.mPoints != null");
            }

            this.paintingContainer.set(0, 0, this.getWidth(), this.getHeight());

            for (int i = 0; i < this.mBytes.length - 1; i++) {
                mPoints[i * 4] = this.paintingContainer.width() * i / (mBytes.length - 1);
                mPoints[i * 4 + 1] = this.paintingContainer.height() / 2
                        + ((byte) (mBytes[i] + 128)) * (this.paintingContainer.height() / 2) / 128;
                mPoints[i * 4 + 2] = this.paintingContainer.width() * (i + 1) / (mBytes.length - 1);
                mPoints[i * 4 + 3] = this.paintingContainer.height() / 2
                        + ((byte) (mBytes[i + 1] + 128)) * (this.paintingContainer.height() / 2) / 128;
            }
            canvas.drawLines(mPoints, this.myPaint);
        } else {
            Log.i(GlobParam.LOG_TAG, "this.mBytes == null");
        }


    }
}
