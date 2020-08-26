package com.peng.animlibrary;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 20:30
 * desc   : 用于设置是否开启 debug 模式，在开启 debug 模式的情况下，会绘制包裹 Text 文本的矩形框
 */
public class Debug {

    public static boolean ENABLED = false;

    public static Paint RED_FILL;
    public static Paint RED_STROKE;
    public static Paint BLUE_STROKE;
    public static Paint YELLOW_STROKE;
    public static Paint GREEN_STROKE;

    static {
        RED_FILL = new Paint();
        BLUE_STROKE = new Paint();
        YELLOW_STROKE = new Paint();
        GREEN_STROKE = new Paint();
        RED_STROKE = new Paint();

        RED_FILL.setColor(Color.RED);
        RED_FILL.setStyle(Paint.Style.FILL);
        RED_FILL.setAntiAlias(true);

        BLUE_STROKE.setColor(Color.BLUE);
        BLUE_STROKE.setStyle(Paint.Style.STROKE);
        BLUE_STROKE.setAntiAlias(true);
        BLUE_STROKE.setStrokeWidth(1);

        YELLOW_STROKE.setColor(Color.YELLOW);
        YELLOW_STROKE.setStyle(Paint.Style.STROKE);
        YELLOW_STROKE.setAntiAlias(true);

        GREEN_STROKE.setColor(Color.GREEN);
        GREEN_STROKE.setStyle(Paint.Style.STROKE);
        GREEN_STROKE.setAntiAlias(true);

        RED_STROKE.setColor(Color.RED);
        RED_STROKE.setStyle(Paint.Style.STROKE);
        RED_STROKE.setAntiAlias(true);
    }
}

