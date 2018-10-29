package com.boomhe.ondraw06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/10/24.
 * 圆形头像
 */
public class AvatarView extends View {

    /**
     * 图片宽度
     */
    public static final float WIDTH = ResourcesHelp.dp2px(200);

    /**
     * 边距距离
     */
    public static final float PADDING = ResourcesHelp.dp2px(50);

    /**
     * 蒙版 和 Bitmap 的高度
     */
    public static final float EDGE_WIDTH = ResourcesHelp.dp2px(10);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    Bitmap bitmap;

    RectF savedArea = new RectF();

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) WIDTH);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        savedArea.set(PADDING,PADDING,PADDING + WIDTH,PADDING + WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.parseColor("#f05b72"));

        // oval 椭圆
        canvas.drawOval(PADDING,PADDING,PADDING + WIDTH,PADDING + WIDTH,paint);
        int saveId = canvas.saveLayer(savedArea, paint);
        canvas.drawOval(PADDING + EDGE_WIDTH,PADDING + EDGE_WIDTH,PADDING + WIDTH - EDGE_WIDTH,PADDING + WIDTH - EDGE_WIDTH,paint);

        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap,PADDING,PADDING,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(saveId);

    }

    private Bitmap getAvatar(int width){
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.idea2, options);
        options.inJustDecodeBounds = false;

        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(getResources(),R.mipmap.idea2,options);

    }
}
