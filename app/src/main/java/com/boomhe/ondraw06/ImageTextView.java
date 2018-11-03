package com.boomhe.ondraw06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/10/30.
 *
 * 绘制文字
 */
public class ImageTextView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    TextPaint textPaint = new TextPaint();

    private static final String STRING = "If the URL mapping isn't set up properly or doesn't exist, an error message appears under the " +
            "URL in the Test App Links window. If the URL mapping exists, Android Studio launches your app in the device or emulator at" +
            " the specified activity without showing the disambiguation dialog (app \"chooser\"), and shows a success message in the App" +
            " Link Testing window. If Android Studio can't successfully launch the app, an error message appears in Android Studio's Run" +
            " window.";

    Bitmap bitmap;

    float[] cutWidth = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        textPaint.setTextSize(ResourcesHelp.dp2px(22));
        paint.setTextSize(ResourcesHelp.dp2px(22));
        bitmap = getAvatar((int) ResourcesHelp.dp2px(100));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1 普通 绘制多行文字
//        StaticLayout staticLayout = new StaticLayout(STRING, textPaint, getWidth(),
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
//
//        staticLayout.draw(canvas);
        // 2 带图片 + 文字
        canvas.drawBitmap(bitmap, getWidth() - ResourcesHelp.dp2px(100), 100, paint);
        // cutWidth 换行截取的文字
        int index = paint.breakText(STRING, true, getWidth(), cutWidth);
        canvas.drawText(STRING, 0, index, 0, 50, paint);

        int oldIndex = index;
        index = paint.breakText(STRING, index, STRING.length(), true, getWidth(), cutWidth);
        canvas.drawText(STRING, 0, oldIndex, 0, 50 + paint.getFontSpacing(), paint);

        oldIndex = index;
        paint.breakText(STRING, index, STRING.length(), true, getWidth() - bitmap.getWidth(), cutWidth);
        canvas.drawText(STRING, 0, oldIndex, 0, 100 + paint.getFontSpacing(), paint);

    }

    private Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.idea2, options);
        options.inJustDecodeBounds = false;

        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(getResources(), R.mipmap.idea2, options);

    }
}
