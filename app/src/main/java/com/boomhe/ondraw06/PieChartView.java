package com.boomhe.ondraw06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/10/24.
 * 饼图
 */
public class PieChartView extends View {

    /**
     * 半径
     */
    private static final float RADIUS = ResourcesHelp.dp2px(120);

    private static final float LENGTH = ResourcesHelp.dp2px(20);

    // 移出的 饼图
    private static final int PULLED_OUT_INDEX = 3;

    int[] angles = {45, 85, 70, 120, 40};
    int[] colors = {Color.parseColor("#2979FF"),
            Color.parseColor("#C2185B"),
            Color.parseColor("#023688"),
            Color.parseColor("#008968"),
            Color.parseColor("#FF8F00")};

    RectF bounds = new RectF();

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
//        bounds.left = getWidth() / 2 - RADIUS;
//        bounds.top = getHeight() / 2 - RADIUS;
//        bounds.right = getWidth() / 2 + RADIUS;
//        bounds.bottom = getHeight() / 2 + RADIUS;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bounds.left = getWidth() / 2 - RADIUS;
        bounds.top = getHeight() / 2 - RADIUS;
        bounds.right = getWidth() / 2 + RADIUS;
        bounds.bottom = getHeight() / 2 + RADIUS;

        int currentAngle = 0;
        int i = 0;
        for (; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            if (PULLED_OUT_INDEX == i) {
                //  Canvas.translate() 来移动扇形
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(bounds, currentAngle, angles[i], true, paint);
            canvas.restore();
            currentAngle += angles[i];
        }

    }
}
