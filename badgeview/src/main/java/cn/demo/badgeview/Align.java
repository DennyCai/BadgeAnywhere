package cn.demo.badgeview;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public interface Align {
    int LEFT = 0x1;
    int RIGHT = 0x2;
    int TOP = 0x4;
    int BOTTOM = 0x8;
    int CENTER = 0x10;


    @Target(ElementType.PARAMETER)
    @IntDef(flag = true,value = {LEFT, RIGHT, TOP, BOTTOM, CENTER})
    @Retention(RetentionPolicy.SOURCE)
    @interface Enum{
    }
}
