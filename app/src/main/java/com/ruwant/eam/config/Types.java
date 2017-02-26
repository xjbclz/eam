package com.ruwant.eam.config;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 00265372 on 2016/12/14.
 */

public class Types {

    //声明一个注解为UserTypes
    //使用@IntDef修饰UserTypes,参数设置为待枚举的集合
    //使用@Retention(RetentionPolicy.SOURCE)指定注解仅存在与源码中,不加入到class文件中
    @IntDef({TECHER, STUDENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserTypes{}

    //声明必要的int常量
    public static final int TECHER = 0;
    public static final int STUDENT = 1;

    private int mType;

    public void setType(@UserTypes int type) {
        mType = type;
    }

    @ UserTypes
    public int getType() {
        return mType;
    }


}
