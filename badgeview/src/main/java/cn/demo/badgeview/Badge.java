package cn.demo.badgeview;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Denny on 2017/7/3 0003.
 * Badge Anywhere
 */

public class Badge {


    private BadgeInterface mInterface;
    private BadgeDrawable mBadge;


    public Badge(BadgeInterface badgeInterface) {
        mInterface = badgeInterface;
        mBadge = new BadgeDrawable();
        mBadge.setBackground(Color.RED);
        mBadge.setTextColor(Color.WHITE);
    }


    /**
     * 设置字体颜色
     * @param color
     */
    public Badge setTextColor(@ColorInt int color) {
        mBadge.setTextColor(color);
        return this;
    }

    /**
     * 创建
     * @param anchor
     * @return
     */
    public static Badge makeBadge(View anchor) {
        if(anchor!=null) {
            Badge badge = new Badge(new BadgeActivity(anchor));
            return badge;
        }
        throw new IllegalArgumentException("anchor is null");
    }

    public static Badge makeBadge(RecyclerView view, int position, @IdRes int anchorId){
        BadgeItemDecoration itemDecoration = new BadgeItemDecoration(anchorId, position);
        itemDecoration.setRecyclerView(view);
        return new Badge(itemDecoration);
    }


    /**
     * 设置背景图
     * @param color
     * @return
     */
    public Badge setBackground(@ColorInt int color) {
        mBadge.setBackground(color);
        return this;
    }


    /**
     * 设置字符串
     * @param text
     * @return
     */
    public Badge setText(String text) {
        mBadge.setText(text);
        return this;
    }

    /**
     * 设置偏移量
     * @param x
     * @param y
     * @return
     */
    public Badge setOffset(int x, int y){
        mInterface.setOffset(x, y);
        return this;
    }

    /**
     * @param align {@link Align}
     * @return
     */
    public Badge setAlign(@Align.Enum int align) {
        mInterface.setAlign(align);
        return this;
    }

    public Badge setMode(@Mode.Enum int mode) {
        mInterface.setMode(mode);
        return this;
    }

    public Badge setTextSize(int size) {
        mBadge.setTextSize(size);
        return this;
    }

    public Badge show() {
        mInterface.setBadgeDrawable(mBadge);
        mInterface.show();
        return this;
    }

    public Badge dismiss(){
        mInterface.hide();
        return this;
    }

}
