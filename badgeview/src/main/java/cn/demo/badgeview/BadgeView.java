package cn.demo.badgeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class BadgeView extends View {

    private final static String TAG = BadgeView.class.getSimpleName();

    private BadgeDrawable mBadge;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBadge = new BadgeDrawable();
        mBadge.setBackground(Color.RED);
        mBadge.setTextSize((int) getResources().getDimension(R.dimen.badge_text_size));
        mBadge.setPadding((int) getResources().getDimension(R.dimen.badge_padding_size));
        mBadge.setTextColor(Color.WHITE);
    }

    public void setBadgeText(String text){
        mBadge.setText(text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isWrapContent(widthMeasureSpec)&&isWrapContent(heightMeasureSpec)) {
            setMeasuredDimension(mBadge.getIntrinsicWidth(), mBadge.getIntrinsicHeight());
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean isWrapContent(int measureSpec) {
        return MeasureSpec.getMode(measureSpec)==MeasureSpec.AT_MOST;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG, String.format("Width:%d, Height:%d", mBadge.getIntrinsicWidth(), mBadge.getIntrinsicHeight()));
        Log.i(TAG, mBadge.getBounds().flattenToString());
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBadge.draw(canvas);
    }

    public int getSize() {
        return Math.max(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setTextColor(int color) {
        mBadge.setTextColor(color);
    }

    public void setTextSize(int size) {
        mBadge.setTextSize(size);
    }

    public void setBadgeDrawable(BadgeDrawable drawbale) {
        mBadge = drawbale;
        requestLayout();
    }

    public void getBadgeRect(Rect rect) {
        rect.set(mBadge.getBounds());
    }
}
