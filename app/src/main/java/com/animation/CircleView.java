package com.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private Paint paint;
    private RectF rect;
    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY, Color.LTGRAY, Color.BLACK, Color.WHITE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private int currentColor = 0;
    
    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rect = new RectF();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeCircleColors();
            }
        }, 1000);
    }
    
    private void changeCircleColors() {
        currentColor++;
        if (currentColor >= colors.length) {
            currentColor = 0;
        }
        invalidate();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeCircleColors();
            }
        }, 1000);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(colors[currentColor]);
        rect.set(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float radius = Math.min(getWidth(), getHeight()) / 16f;
        for (int i = 0; i < 15; i++) {
            float x = centerX + radius * 2.5f * (i % 5 - 2);
            float y = centerY + radius * 2.5f * (i / 5 - 1);
            paint.setColor(colors[i]);
            canvas.drawCircle(x, y, radius, paint);
        }
    }
}