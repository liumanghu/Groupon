package com.example.groupon.UI.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.groupon.R;

public class Indicator extends View {
    //前景图像画笔
    Paint forePaint;
    //背景图像画笔
    Paint backPaint;
    //偏移量
    float offset;
    //pager数量
    int mNumber = 3;
    //圆的半径
    int radius = 10;
    //背景画笔的颜色
    int bgColor = Color.GRAY;
    //前景画笔的颜色
    int foreColor = Color.GRAY;
    
    public Indicator(Context context) {
        super(context);
    }

    public Indicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        mNumber = typedArray.getInteger(R.styleable.Indicator_indicator_number,mNumber);
        radius = typedArray.getInteger(R.styleable.Indicator_indicator_radius,radius);
        foreColor = typedArray.getColor(R.styleable.Indicator_indicator_foreColor,foreColor);
        bgColor = typedArray.getColor(R.styleable.Indicator_indicator_bgColor,bgColor);


        forePaint = new Paint();
        forePaint.setAntiAlias(true); //设置抗锯齿
        forePaint.setStyle(Paint.Style.FILL); //设置为实心的格式
        forePaint.setColor(foreColor);  //设置颜色
        forePaint.setStrokeWidth(2); //设置笔的宽度

        backPaint = new Paint();
        backPaint.setAntiAlias(true); //设置抗锯齿
        backPaint.setStyle(Paint.Style.STROKE); //设置为空心的格式
        backPaint.setColor(bgColor);  //设置颜色
        backPaint.setStrokeWidth(2); //设置笔的宽度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mNumber; i++) {
            canvas.drawCircle(30 + 30 * i,radius,radius,backPaint);
        }
        canvas.drawCircle(30 + offset,radius,radius,forePaint);
    }

    //对控件进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getWidthMeasure(widthMeasureSpec),getHeightMeasure(heightMeasureSpec));
    }

    private int getHeightMeasure(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        
        int result = 0;  //返回结果
        
        if (mode == MeasureSpec.EXACTLY){
            result = MeasureSpec.makeMeasureSpec(size,MeasureSpec.EXACTLY);
        }else{
            result = MeasureSpec.makeMeasureSpec( getPaddingTop() + 2 * radius + getPaddingBottom(),MeasureSpec.AT_MOST);
        }
        return result;
    }

    private int getWidthMeasure(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        int result = 0;
        if (mode == MeasureSpec.EXACTLY){
            result = MeasureSpec.makeMeasureSpec(size,MeasureSpec.EXACTLY);
        }else{
            result = MeasureSpec.makeMeasureSpec(getPaddingLeft() + (mNumber + 1) * 30 + getPaddingRight(),MeasureSpec.AT_MOST);
        }
        return result;
    }

    /**
     * @param position 表示当前page的位置
     * @param positionOffset  表示当前pageView的偏移比例
     */
    public void setPositionOffset(int position,float positionOffset) {
        offset = position * 30 + positionOffset * 30;
        invalidate();
    }
}
