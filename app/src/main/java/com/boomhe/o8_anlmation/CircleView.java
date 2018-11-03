package com.boomhe.o8_anlmation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/11/1.
 */
public class CircleView extends View {


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float radius = ResourcesHelp.dp2px(10);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        // 通知 View 调用 onDraw
        invalidate();
    }

    {
        paint.setColor(Color.parseColor("#f05b72"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}
