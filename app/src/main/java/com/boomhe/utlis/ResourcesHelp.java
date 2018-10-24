package com.boomhe.utlis;

import android.content.res.Resources;
import android.util.TypedValue;

import java.lang.reflect.Type;

/**
 * @author TY on 2018/10/24.
 */
public class ResourcesHelp {

    /**
     * dp To px
     * @param dp
     * @return
     */
    public static float dp2px(float dp){

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp
                ,Resources.getSystem().getDisplayMetrics());
    }
}
