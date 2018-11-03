package com.boomhe.ondraw07;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.boomhe.ondraw06.R;
import com.boomhe.utlis.ResourcesHelp;

/**
 * @author TY on 2018/11/2.
 */
public class ButtonView extends View {

    private float width;
    private float height;
    private String text;
    private float padding;
    private float angle;
    private float textSize;
    private int background;
    private int roundColor;
    private int textColor;

    Rect rect = new Rect();

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public ButtonView(Context context) {
        super(context);
        init(context, null, 0);

    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context);
        init(context, attrs, 0);

    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    void init(Context context, AttributeSet attrs, int defStyleAttr) {


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.buttonStyle, defStyleAttr, 0);

        width = typedArray.getDimension(R.styleable.buttonStyle_button_width, ResourcesHelp.dp2px(50));
        height = typedArray.getDimension(R.styleable.buttonStyle_button_height, ResourcesHelp.dp2px(50));
        text = typedArray.getString(R.styleable.buttonStyle_button_text);
        if (TextUtils.isEmpty(text)) {
            text = "确定";
        }
        background = typedArray.getColor(R.styleable.buttonStyle_button_background, Color.parseColor("#494e8f"));
        roundColor = typedArray.getColor(R.styleable.buttonStyle_button_roundColor, 0);
        textColor = typedArray.getColor(R.styleable.buttonStyle_button_textColor, Color.parseColor("#fffffb"));
        padding = typedArray.getDimension(R.styleable.buttonStyle_button_padding, 0);
        angle = typedArray.getDimension(R.styleable.buttonStyle_button_angle, ResourcesHelp.dp2px(20));
        textSize = typedArray.getDimensionPixelSize(R.styleable.buttonStyle_button_textSize, (int) ResourcesHelp.dp2px(20));


        typedArray.recycle();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、先绘制 背景
        paint.setColor(background);
        canvas.drawRoundRect(0 + padding, 0 + padding, width + padding, height + padding, angle, angle, paint);

        // 2、绘制 外边
        if (roundColor == 0) {
            roundColor = background;
        }
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ResourcesHelp.dp2px(1.5f));
        canvas.drawRoundRect(0 + padding, 0 + padding, width + padding, height + padding, angle, angle, paint);


        // 3、再绘制文字，（否则被背景遮盖不可见）
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        // 4、处理文字纵向居中
        textPaint.getTextBounds(text, 0, text.length(), rect);
        float offset = (rect.top + rect.bottom) / 2;

        canvas.drawText(text, width / 2 + padding, height / 2 - offset + padding, textPaint);

    }


}
