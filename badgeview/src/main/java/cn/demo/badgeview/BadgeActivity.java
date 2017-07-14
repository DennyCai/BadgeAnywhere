package cn.demo.badgeview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Denny on 2017/7/10 0010.
 */

public class BadgeActivity implements BadgeInterface {

    private View mAnchor;
    private FrameLayout mFrameLayout;
    private BadgeLayout mLayout;
    private BadgeView mBadgeView;
    private Context mContext;
    private View.OnLayoutChangeListener mOnLayoutChange;

    public BadgeActivity(View anchor){
        mAnchor = anchor;
        mContext = mAnchor.getContext();
        mLayout = new BadgeLayout();
        if(!resolveTopLayout()){
            throw new IllegalStateException("anchor view must add to contentView");
        }
        createBadge();
    }

    private void createBadge() {
        mBadgeView = new BadgeView(mContext){
            @Override
            public void discard() {
                hide();
            }
        };
        mBadgeView.setOnTouchListener(new DragDispatcher(mBadgeView));
    }

    public void setBadgeView(BadgeView badgeView){
        mBadgeView = badgeView;
    }

    @Override
    public void show(){
        mFrameLayout.addView(mBadgeView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Rect rect = new Rect();
        mBadgeView.getBadgeRect(rect);
        mLayout.setBadgeRect(rect);
        attachAnchor();
    }

    private void attachAnchor() {
        mAnchor.addOnLayoutChangeListener(mOnLayoutChange = new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                /*if(left-oldLeft==0 && top-oldTop==0 && right-oldRight==0 && bottom-oldBottom==0){
                    return;
                }*/
                Rect anchorRect = new Rect();
                int[] location = new int[]{0, 0};
                int[] anchorLoc = new int[]{0, 0};

                mAnchor.getLocalVisibleRect(anchorRect);
                mFrameLayout.getLocationInWindow(location);
                mAnchor.getLocationInWindow(anchorLoc);

                Rect layoutRect = new Rect();
                layoutRect.left = anchorLoc[0];
                layoutRect.top = anchorLoc[1] - location[1];
                layoutRect.right = layoutRect.left + anchorRect.width();
                layoutRect.bottom = layoutRect.top + anchorRect.height();
                anchorRect.set(layoutRect);

                mLayout.setTargetRect(anchorRect);
                updateBadgeView(mLayout.resolveBadgePosition());
            }
        });
        mAnchor.requestLayout();
    }

    @Override
    public void hide() {
        if(mFrameLayout!=null&&mBadgeView!=null){
            mFrameLayout.removeView(mBadgeView);
            detachAnchor();
        }
    }

    private void detachAnchor() {
        if(mAnchor!=null&&mOnLayoutChange!=null){
            mAnchor.removeOnLayoutChangeListener(mOnLayoutChange);
        }
    }

    private void updateBadgeView(Point point) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = point.x;
        lp.topMargin = point.y;
        mFrameLayout.updateViewLayout(mBadgeView, lp);
    }

    private boolean resolveTopLayout() {
        View content = (View) mAnchor.getParent();
        while (content.getId() != android.R.id.content) {
            content = (View) content.getParent();
            if (content == null) {
                return false;
            }
        }
        if(content instanceof FrameLayout) {
            mFrameLayout = (FrameLayout) content;
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void setAlign(@Align.Enum int align) {
        mLayout.setAlign(align);
    }

    @Override
    public void setMode(@Mode.Enum int mode) {
        mLayout.setMode(mode);
    }

    @Override
    public void setOffset(int x, int y) {
        mLayout.setOffset(x, y);
    }

    @Override
    public void setBadgeDrawable(BadgeDrawable drawable) {
        mLayout.setBadgeRect(drawable.getBounds());
        mBadgeView.setBadgeDrawable(drawable);
    }
}
