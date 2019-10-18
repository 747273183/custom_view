package com.example.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundProgressBar extends View {

    private int mRadius;//半径
    private int mColor;//颜色
    private int mLineWidth;//线高
    private int mTextSize;//字体大小
    private int mProgress;//进度

    private Paint mPaint;//画笔

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);
         mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
         mColor=typedArray.getColor(R.styleable.RoundProgressBar_color,0XFFFF0000);
         mLineWidth= (int) typedArray.getDimension(R.styleable.RoundProgressBar_line_width,dp2px(3));
         mTextSize= (int) typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize,dp2px(16));
         mProgress=typedArray.getInt(R.styleable.RoundProgressBar_android_progress,0);

        typedArray.recycle();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//平滑字体,反锯齿
        mPaint.setColor(mColor);

    }

    //2.测量onMeasure
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //测量宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWith = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(needWith, widthSize);
            } else {
                width = needWith;
            }
        }

        //测量高度

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(needHeight, heightSize);
            } else {
                height = needHeight;
            }
        }

        //设置测试后的值
        setMeasuredDimension(width, height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int measureHeight() {
        return 0;
    }

    private int measureWidth() {
        return 0;
    }

    //dp转px
    private float dp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);



    }


    //状态存储
    private static  final  String INSTANCE="instance";
    private static  final  String KEY_PROGRESS="key_progress";
    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {

        Bundle bundle=new Bundle();
        bundle.putInt(KEY_PROGRESS,mProgress);
        bundle.putParcelable(INSTANCE,super.onSaveInstanceState());
        return  bundle;
    }

    //状态恢复
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle)
        {
            Bundle bundle= (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mProgress = bundle.getInt(KEY_PROGRESS);
            return;
        }
        super.onRestoreInstanceState(state);

    }

}