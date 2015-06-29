package com.example.hiciak.myringtoneapp.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by hiciak on 29/06/15.
 */
public class MyWaveformView extends View {

    private Paint myPaint;
    private Rect paintingContainer;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0f, 0f, this.getWidth(), this.getHeight(), this.myPaint);
    }
}
