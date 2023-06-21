package com.example.no2uaspapb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();

    ObjectAnimator mFadeIn, mFadeOut, mRotation;

    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        mFadeOut = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0);
        mFadeIn = ObjectAnimator.ofFloat(mImgView, "alpha", 0, 1f);
        mRotation = ObjectAnimator.ofFloat(mImgView, "rotationY", 180);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.background, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead(vWidth, vHeight);
        drawRightEye(vWidth, vHeight);
        drawLeftEye(vWidth, vHeight);
        drawEyeConnector(vWidth, vHeight);
        animateBaymax();

    }

    public void drawHead (int mWidth,int mHeight){
        RectF ovalHead = new RectF();
        ovalHead.set(mWidth/2-300, mHeight/2-200, mWidth/2+300,mHeight/2+200);
        mCanvas.drawOval(ovalHead, mHeadPaint);
    }

    public void drawRightEye(int mWidth,int mHeight){
        mCanvas.drawCircle(mWidth/2+150, mHeight/2, 50, mCirclePaint);
    }

    public void drawLeftEye(int mWidth,int mHeight){
        mCanvas.drawCircle(mWidth/2-150, mHeight/2, 50, mCirclePaint);
    }

    public void drawEyeConnector(int mWidth,int mHeight){
        mRect.set(mWidth/2-150, mHeight/2-10, mWidth/2+150, mHeight/2+10);
        mCanvas.drawRect(mRect, mCirclePaint);
    }
    
    public void animateBaymax(){
        mFadeIn.setDuration(500);
        mFadeIn.start();

        mRotation.setStartDelay(500);
        mRotation.getStartDelay();
        mRotation.setDuration(2000);
        mRotation.start();

        mFadeOut.setStartDelay(3000);
        mFadeOut.getStartDelay();
        mFadeOut.setDuration(500);
        mFadeOut.start();
    }
}