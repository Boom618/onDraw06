package com.boomhe.utlis;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import com.boomhe.ondraw06.R;

/**
 * @author TY on 2018/10/24.
 */
public class ResourcesHelp {

    /**
     * dp To px
     *
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp
                , Resources.getSystem().getDisplayMetrics());
    }


    public static Bitmap getAvatar(Resources resources, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.mipmap.idea2, options);
        options.inJustDecodeBounds = false;

        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(resources, R.mipmap.idea2, options);

    }


    public static float getZForcamera() {
        return -6 * Resources.getSystem().getDisplayMetrics().density;
    }
}
