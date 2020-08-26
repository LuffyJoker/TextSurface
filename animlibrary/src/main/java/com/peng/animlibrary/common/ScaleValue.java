package com.peng.animlibrary.common;

import android.graphics.PointF;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 20:24
 * desc   : Text 的缩放属性
 */
public class ScaleValue {

    /**
     * 缩放比例
     */
    private PointF scale = new PointF(1, 1);

    /**
     * 缩放的中心点
     */
    private PointF pivot = new PointF();

    public void setValue(float scale) {
        this.scale.set(scale, scale);
    }

    public void setValueX(float x) {
        scale.set(x, scale.y);
    }

    public void setValueY(float y) {
        scale.set(scale.x, y);
    }

    public float getScaleX() {
        return scale.x;
    }

    public float getScaleY() {
        return scale.y;
    }

    public PointF getPivot() {
        return pivot;
    }

    /**
     * 重置缩放属性
     */
    public void reset() {
        scale.set(1, 1);
        pivot.set(0, 0);
    }
}
