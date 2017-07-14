package cn.demo.badgeview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class BadgeDrawable extends GradientDrawable implements Moveable{

    private final static  String TAG = BadgeDrawable.class.getSimpleName();


    private String mText;
    private Paint mTextPaint;
    private int mTextColor = Color.BLACK;
    private int mTextSize = 30;
    private Rect mTextRect;
    private int mPadding = 20;

    public BadgeDrawable() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextRect = new Rect();
        resolveSize();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if(!TextUtils.isEmpty(mText)){
            canvas.save();
            canvas.translate(0, mTextRect.height()/2);
            canvas.drawText(mText, getIntrinsicWidth()/2, getIntrinsicHeight()/2, mTextPaint);
            canvas.restore();
        }
    }

    public void setText(String text){
        mText = text;
        resolveSize();
    }

    public void setTextSize(int size){
        mTextSize = size;
        mTextPaint.setTextSize(mTextSize);
        resolveSize();
    }

    public void setBackground(int color){
        setColor(color);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        setCornerRadius((width>height?width:height));
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        int width = right - left, height = bottom - top;
        if(width < height){
            width = height;
        }
        setSize(width, height);
        super.setBounds(left, top, left + width, top + height);

    }

    @Override
    public void setBounds(@NonNull Rect bounds) {
        super.setBounds(bounds);
        setBounds(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    private void resolveSize() {
        if(!TextUtils.isEmpty(mText)) {
            mTextRect = new Rect();
            mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
            setBounds(0, 0, mTextRect.width() + mPadding, mTextRect.height() + mPadding );
        }else{
            setBounds(0, 0, 30, 30);
        }
    }


    public void setTextColor(int color) {
        mTextColor = color;
        mTextPaint.setColor(mTextColor);
    }

    public void setPadding(int padding) {
        mPadding = padding;
        resolveSize();
    }

    @Override
    public void moveX(float x) {
        Rect b  = getBounds();
        setBounds(Math.round(x), b.top, Math.round(b.height() + x), b.bottom);
    }

    @Override
    public void moveY(float y) {
        Rect b = getBounds();
        setBounds(b.left, Math.round(y) , b.right, Math.round(b.height() + y));
    }

    @Override
    public float getX() {
        return getBounds().left;
    }

    @Override
    public float getY() {
        return getBounds().top;
    }

    @Override
    public void discard() {

    }
}
