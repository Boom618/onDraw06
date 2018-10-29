package com.boomhe.ondraw06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/10/29.
 */
public class SportsView extends View {


    public static final float RADIUS = ResourcesHelp.dp2px(150);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Rect  保存矩形四个顶点坐标
    Rect rect = new Rect();

    // 创建一个初始值 都为 0 的矩形
    //RectF rectF = new RectF();


    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#ef5b9c"));

        // 字体大小
        paint.setTextSize(ResourcesHelp.dp2px(50));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制环
        paint.setStrokeWidth(ResourcesHelp.dp2px(20));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        // 绘制进度条
        paint.setColor(Color.parseColor("#d71345"));
        // 环 圆角处理
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                -90, 300, false, paint);

        // 文字居中参考线
        paint.setStrokeWidth(ResourcesHelp.dp2px(2));
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        // 绘制文字
        paint.setStyle(Paint.Style.FILL);
        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;
        canvas.drawText("运动环", getWidth() / 2, getHeight() / 2 - offset, paint);
    }
}
