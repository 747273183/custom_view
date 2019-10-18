package com.example.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {
    private static final String TAG = "TestView";

    private String stringTest = "imooc";
    private Paint mPaint;

    //1.自定义属性的声明和获取
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        mPaint=init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestView);

        boolean booleanTest = typedArray.getBoolean(R.styleable.TestView_test_boolean, false);
        float dimensionTest = typedArray.getDimension(R.styleable.TestView_test_dimension, 0);
        int enumTest = typedArray.getInt(R.styleable.TestView_test_enum, 1);
//        stringTest = typedArray.getString(R.styleable.TestView_test_string);
        int integerTest = typedArray.getInteger(R.styleable.TestView_test_integer, -1);

        //第二种获得属性值的方式
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.TestView_test_string:
                    stringTest = typedArray.getString(R.styleable.TestView_test_string);
                    break;
            }
        }

        Log.d(TAG, "booleanTest: " + booleanTest + ",dimensionTest:" + dimensionTest + ",enumTest=" + enumTest + ",stringTest=" + stringTest + ",integerTest=" + integerTest);


        typedArray.recycle();
    }

    private Paint init() {
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);//sTROKE 空心 full 实心
        paint.setStrokeWidth(6);
        paint.setColor(0xFFFF0000);
        paint.setAntiAlias(true);//抗锯齿
        return paint;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-mPaint.getStrokeWidth()/2,mPaint);
        mPaint.setStrokeWidth(1);
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,mPaint);//画一个横向的线

        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight() ,mPaint); //画一个纵向的线

        //画文本
        mPaint.setTextSize(70);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        canvas.drawText(stringTest,0,getHeight(),mPaint);

    }

    private int measureHeight() {
        return 0;
    }

    private int measureWidth() {
        return 0;
    }
}
