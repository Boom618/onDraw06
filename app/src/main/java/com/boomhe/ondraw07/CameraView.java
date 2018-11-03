package com.boomhe.ondraw07;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/10/30.
 *
 * 绘制 几何变换
 */
public class CameraView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {
        camera.rotateX(45);
        camera.setLocation(0, 0, ResourcesHelp.getZForcamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 切图
        //canvas.clipRect(0,0,400,400);
        // canvas.clipOutRect(200,200,400,400);

        // dx dy : left +  ( width/2 )
        // 参考线
        paint.setColor(Color.parseColor("#f05b72"));
        paint.setStrokeWidth(ResourcesHelp.dp2px(4));
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);


        // 绘制上半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        canvas.clipRect(- 600, - 600, 600, 0);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(ResourcesHelp.getAvatar(getResources(), 600), 100, 100, paint);
//        canvas.restore();
//
//        // 绘制下半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        camera.applyToCanvas(canvas);
//        canvas.clipRect(- 600, 0, 600, 600);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(ResourcesHelp.getAvatar(getResources(), 600), 100, 100, paint);
//        canvas.restore();

        // 绘制上半部分
        canvas.save();
        canvas.translate(300, 300);
        canvas.rotate(-20);
        // 切割
        canvas.clipRect(-600, -600, 600, 0);
        // 旋转
        canvas.rotate(20);
        // 翻转
        canvas.translate(-300, -300);
        canvas.drawBitmap(ResourcesHelp.getAvatar(getResources(), 400), 100, 100, paint);
        canvas.restore();


        // 绘制下半部分
        canvas.save();
        canvas.translate(300, 300);
        canvas.rotate(-20);
        // 应用到 canvas 上
        camera.applyToCanvas(canvas);
        // 切割
        canvas.clipRect(-600, 0, 600, 600);
        // 旋转
        canvas.rotate(20);
        // 翻转
        canvas.translate(-300, -300);
        canvas.drawBitmap(ResourcesHelp.getAvatar(getResources(), 400), 100, 100, paint);
        canvas.restore();
    }


}
