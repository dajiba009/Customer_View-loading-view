package com.example.custom_view_circleloading;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class TestCustomView extends View {

    private ValueAnimator colorAnim;
    private int color;
    private int startColor = Color.BLUE;
    private int endColor;
    private LinearGradient mLinearGradient;

    private int tempWidth;
    private int tempHight;

    public TestCustomView(Context context) {
        super(context);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(color == 0){
            color = Color.BLACK;
        }
        Paint paint = new Paint();
        int width = getWidth();
        int height = getHeight();
       // paint.setColor(color);

        //设置paint的颜色渐变
        mLinearGradient = new LinearGradient(10,10,width,tempHight,startColor,endColor, Shader.TileMode.MIRROR);
        paint.setShader(mLinearGradient);
        //========================
        paint.setAntiAlias(true);
        canvas.drawRect(10,10,width,tempHight,paint);
    }

    public void startAnimation(int startColor, final int endColor){
        colorAnim = ObjectAnimator.ofInt(this,"backgroundColor",startColor,endColor);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                color = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        colorAnim.start();
    }

    public void argbChange(float percent){
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        int currentColor = (int) argbEvaluator.evaluate(percent,Color.BLUE,Color.RED);
        color = currentColor;
        postInvalidate();
    }

    public void gradientColor(float percent,int startColor,int endColor){
        this.startColor = startColor;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        int currentColor = (int) argbEvaluator.evaluate(percent,startColor,endColor);
        this.endColor = currentColor;
        this.tempHight = (int) (percent * getHeight());
        postInvalidate();
    }
}
