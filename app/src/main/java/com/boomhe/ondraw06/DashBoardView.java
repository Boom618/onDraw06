package com.boomhe.ondraw06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

import java.util.logging.Level;

/**
 * @author TY on 2018/10/24.
 * 仪表盘
 */
public class DashBoardView extends View {

    /**
     * 半径
     */
    private static final float RADIUS = ResourcesHelp.dp2px(150);

    /**
     * 弧度
     */
    private static final int ANGLE = 120;

    /**
     * 画笔 Paint 的宽度
     */
    private static final float LENGTH = ResourcesHelp.dp2px(2);

    private PathDashPathEffect effect;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path dash = new Path();


    public DashBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *  {} 大括号之间的代码 是在 构造方法中的 super 后面执行的
     */

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#f05b72"));
        paint.setStrokeWidth(LENGTH);

        RectF rectF = new RectF();
        rectF.left = getWidth() / 2 - RADIUS;
        rectF.top = getHeight() / 2 - RADIUS;
        rectF.right = getWidth() / 2 + RADIUS;
        rectF.bottom = getHeight() / 2 + RADIUS;

        dash.addRect(0, 0, LENGTH, ResourcesHelp.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(rectF, 90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);

        float length = pathMeasure.getLength();
        // path 、间距、抵消数量、样式:TRANSLATE 、ROTATE、 MORPH
        effect = new PathDashPathEffect(dash, (length - LENGTH) / 20, 0, PathDashPathEffect.Style.ROTATE);

    }

    /**
     * 绘制部分
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画线（弧度盘）
        // startAngle ： 起始角度 sweepAngle : 扫描的角度
        RectF rectF = new RectF();
        rectF.left = getWidth() / 2 - RADIUS;
        rectF.top = getHeight() / 2 - RADIUS;
        rectF.right = getWidth() / 2 + RADIUS;
        rectF.bottom = getHeight() / 2 + RADIUS;

        canvas.drawArc(rectF, 90 + ANGLE / 2, 360 - ANGLE, false, paint);

        // 2、 画刻度
        paint.setPathEffect(effect);
        canvas.drawArc(rectF, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        // 3、画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(17))) * ResourcesHelp.dp2px(100) + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(17))) * ResourcesHelp.dp2px(100) + getHeight() / 2,
                paint);

    }

    /**
     * @param mark 第几个刻度
     * @return 弧度
     */
    int getAngleFromMark(int mark) {
        return (90 + ANGLE / 2 + 360 - ANGLE) / 20 * mark;
//        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);

    }
}
