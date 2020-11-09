package com.example.custom_view_circleloading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.Utils.SizeUtils;

import androidx.annotation.Nullable;

public class CircleView extends View {

    private static final int CLOCKWISE = 360;
    private static final int ANTICLOCKWISE = -360;

    private Paint mPaint;
    private float currentPercent = 0.0f;
    private LinearGradient mLinearGradient;
    private int startColor;
    private int endColor;
    private float mProgressWidth;
    private float mOutsideRadius;
    private int mDirection;
    //当前是逆时针还是顺时针
    private int currentCock;


    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniAttrs(context,attrs);
    }

    private void iniAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        startColor = a.getColor(R.styleable.CircleView_startColor,Color.BLUE);
        endColor = a.getColor(R.styleable.CircleView_endColor,Color.RED);
        mProgressWidth = a.getDimension(R.styleable.CircleView_progress_width, 60.0f);
        mOutsideRadius = a.getDimension(R.styleable.CircleView_outside_radius,150.0f);
        mDirection = a.getInt(R.styleable.CircleView_direction,0);
        a.recycle();
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public float getProgressWidth() {
        return mProgressWidth;
    }

    public void setProgressWidth(float progressWidth) {
        mProgressWidth = progressWidth;
    }

    public float getOutsideRadius() {
        return mOutsideRadius;
    }

    public void setOutsideRadius(float outsideRadius) {
        mOutsideRadius = outsideRadius;
    }

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int direction) {
        mDirection = direction;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断是顺时针还是逆时针
        if(mDirection == 0){
            currentCock = CLOCKWISE;
        }else {
            currentCock = ANTICLOCKWISE;
        }
        int circlePoint = getWidth() / 2;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setColor(Color.GRAY);

        //画空心圆
        canvas.drawCircle(circlePoint, circlePoint, mOutsideRadius, mPaint); //画出圆

//        mPaint.setColor(Color.RED);
        mLinearGradient = new LinearGradient(0,0,60.0f,currentPercent*getWidth(),startColor,endColor, Shader.TileMode.MIRROR);
        mPaint.setShader(mLinearGradient);
        RectF rectF = new RectF(circlePoint - mOutsideRadius, circlePoint - mOutsideRadius, circlePoint + mOutsideRadius, circlePoint + mOutsideRadius);
        canvas.drawArc(rectF,-90,currentCock*(currentPercent),false,mPaint);
    }

    public void drawProgress(float percent){
        currentPercent = percent;
        postInvalidate();
    }
}
