package com.plusend.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.plusend.viewdemo.R;
import com.plusend.viewdemo.utils.Logger;

/**
 * Created by plusend on 16/5/5.
 */
public class CircleView extends View {
    private static final String TAG = "CircleView";

    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * wrap_content 默认大小
     */
    private int mWidth = 600;
    private int mHeight = 600;

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Logger.d(TAG, "CircleView 2 parameters");
    }

    public CircleView(Context context) {
        super(context);
        Logger.d(TAG, "CircleView 1 parameters");
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Logger.d(TAG, "CircleView 3 parameters");
        /**
         * 支持自定义属性
         */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        a.recycle();
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * version 1. 不兼容 padding
         */
//        int width = getWidth();
//        int height = getHeight();
//        int radius = Math.min(width, height) / 2;
//        canvas.drawCircle(width / 2, height / 2, radius, mPaint);

        /**
         * version 2. 兼容 padding
         */
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Logger.d(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        /**
         * 兼容 wrap_content
         */
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            Logger.d(TAG, "widthSpecMode + heightSpecMode");
            setMeasuredDimension(mWidth, mHeight);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            Logger.d(TAG, "widthMeasureSpec");
            setMeasuredDimension(mWidth, heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            Logger.d(TAG, "heightMeasureSpec");
            setMeasuredDimension(widthSpecSize, mHeight);
        }else {
            Logger.d(TAG, "widthSpecMode = " + widthSpecMode + " heightSpecMode = " + heightSpecMode
                    + " AT_MOST = " + MeasureSpec.AT_MOST);
        }
    }
}
