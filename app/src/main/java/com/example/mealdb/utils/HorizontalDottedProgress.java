package com.example.mealdb.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.example.mealdb.R;

public class HorizontalDottedProgress extends View {

    private static final String TAG = "HorizontalDottedProgres";

    //actual dot radius
    private int mDotRadius = 8;

    //Bounced Dot Radius
    private int mBounceDotRadius = 11;

    //to get identified in which position dot has to bounce
    private int  mDotPosition;

    //specify how many dots you need in a progressbar
    private int mDotAmount = 15;

    public HorizontalDottedProgress(Context context) {
        super(context);
    }

    public HorizontalDottedProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalDottedProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //Method to draw your customized dot on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        //set the color for the dot that you want to draw
        paint.setColor(getResources().getColor(R.color.my_hint_color));

        //function to create dot
        createDot(canvas,paint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //Animation called when attaching to the window, i.e to your screen
        startAnimation();
    }

    private void createDot(Canvas canvas, Paint paint) {

        // set progress bar with 15 dots, so repeat and when i = mDotPosition then increase the radius of dot i.e mBounceDotRadius
        for(int i = 0; i < mDotAmount; i++ ){
            if(i == mDotPosition){
                canvas.drawCircle(10+(i*20), mBounceDotRadius, mBounceDotRadius, paint);
            }else {
                canvas.drawCircle(10+(i*20), mBounceDotRadius, mDotRadius, paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;

        //calculate the view width
        int calculatedWidth = (20*9);

        width = calculatedWidth;
        height = (mBounceDotRadius*2);

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    private void startAnimation() {
        BounceAnimation bounceAnimation = new BounceAnimation();
        bounceAnimation.setDuration(200);
        bounceAnimation.setRepeatCount(Animation.INFINITE);
        bounceAnimation.setInterpolator(new LinearInterpolator());
        bounceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                mDotPosition++;
                //when mDotPosition == mDotAmount , then start again applying animation from 0th positon , i.e  mDotPosition = 0;
                if (mDotPosition == mDotAmount) {
                    mDotPosition = 0;
                }
                Log.d(TAG,"----On Animation Repeat----");

            }
        });
        startAnimation(bounceAnimation);
    }

    private class BounceAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            //call invalidate to redraw your view again.
            invalidate();
        }
    }
}