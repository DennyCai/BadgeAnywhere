package cn.demo.badgeview;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public interface Mode {
    int COSSING = 0x1;
    int CONTAIN = 0x2;

    @Target(ElementType.PARAMETER)
    @IntDef(flag = true,value = {COSSING, CONTAIN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Enum{
    }
}
