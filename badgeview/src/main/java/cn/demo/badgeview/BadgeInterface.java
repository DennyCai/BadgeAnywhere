package cn.demo.badgeview;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public interface BadgeInterface {

    void setAlign(@Align.Enum int align);

    void setMode(@Mode.Enum int mode);

    void setOffset(int x, int y);

    void setBadgeDrawable(BadgeDrawable drawable);

    void show();

    void hide();

}
