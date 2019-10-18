package com.example.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);
         mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
         mColor=typedArray.getColor(R.styleable.RoundProgressBar_color,0XFFFF0000);
         mLineWidth= (int) typedArray.getDimension(R.styleable.RoundProgressBar_line_width,dp2px(3));
         mTextSize= (int) typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize,dp2px(16));
         mProgress=typedArray.getInt(R.styleable.RoundProgressBar_android_progress,30);

        typedArray.recycle();
        //要放在这里,因为初始化之间要设置一些值
        initPaint();
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
        //绘制一个圆
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔样式
        mPaint.setStrokeWidth(mLineWidth*1.0f/4);
        int width=getWidth();
        int height=getHeight();
        canvas.drawCircle(width/2,height/2,
                width/2-getPaddingLeft()-mPaint.getStrokeWidth()/2,
                mPaint);

        //绘制一个圆弧
        mPaint.setStrokeWidth(mLineWidth);
        canvas.save();
        canvas.translate(getPaddingLeft(),getPaddingTop());
        float angle=mProgress*1.0f/100*360;
        canvas.drawArc(new RectF(
                0,
                0,
                width-getPaddingLeft()*2,
            height-getPaddingLeft()*2),
                0,
                angle,
                false,
                mPaint);
        canvas.restore();

        //绘制文本
        String mText=mProgress+"%";
        mPaint.setStrokeWidth(0);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);

        int y=getHeight()/2;
        Rect bound=new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),bound);
        int textHeight=bound.height();
        canvas.drawText(mText,0,mText.length(),getWidth()/2,y+textHeight/2,mPaint);


        //画一个横线,辅助查看进度文本是不是在正中心
        mPaint.setStrokeWidth(0);
        canvas.drawLine(0,height/2,width,height/2,mPaint);


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
