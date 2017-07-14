package cn.demo.badgeview;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Denny on 2017/7/10 0010.
 * 负责布局位置,输出的为x,y值,即左上角位置
 */

public class BadgeLayout {

    private Rect mTargetRect;
    private Rect mBadgeRect;
    private int mAlign;
    private int mMode;
    private Point mOffset;

    public BadgeLayout() {
        mTargetRect = new Rect();
        mBadgeRect = new Rect();
        mAlign = Align.LEFT | Align.TOP;
        mMode = Mode.CONTAIN;
        mOffset = new Point();
    }

    public void setTargetRect(Rect targetRect) {
        mTargetRect = targetRect;
    }

    public void setBadgeRect(Rect badgeRect) {
        mBadgeRect = badgeRect;
    }

    public void setAlign(@Align.Enum int align) {
        mAlign = align;
    }

    public void setMode(@Mode.Enum int mode) {
        mMode = mode;
    }

    public Point resolveBadgePosition() {
        Point out = new Point();
        resolveAlign(out);
        resolveMode(out);
        resolveOffset(out);
        return out;
    }

    private void resolveOffset(Point out) {
        out.offset(mOffset.x, mOffset.y);
    }

    private void resolveMode(Point out) {
        int width = mBadgeRect.width();
        int height = mBadgeRect.height();
        int offX = 0, offY = 0;
        if(isMode(mMode, Mode.COSSING)){
            offX = -(width>>1);
            offY = - (height >>1);

        }else{
            if(isAlign(mAlign, Align.RIGHT)){
                offX = -width;
            }
            if(isAlign(mAlign, Align.BOTTOM)){
                offY = -height;
            }
        }

        out.x += offX;
        out.y += offY;

    }

    private boolean isMode(int m1, int m2) {
        return (m1 & m2) == m2;
    }

    private void resolveAlign(Point out) {
        if (isAlign(mAlign, Align.RIGHT)) {
            out.x = mTargetRect.right;
        }else{
            out.x = mTargetRect.left;
        }
        if (isAlign(mAlign, Align.BOTTOM)) {
            out.y = mTargetRect.bottom;
        }else{
            out.y = mTargetRect.top;
        }

    }

    private static boolean isAlign(@Align.Enum int a1, @Align.Enum int a2) {
        return (a1 & a2) == a2;
    }

    public void setOffset(Point offset) {
        mOffset = offset;
    }

    public void setOffset(int x, int y) {
        setOffset(new Point(x, y));
    }
}
