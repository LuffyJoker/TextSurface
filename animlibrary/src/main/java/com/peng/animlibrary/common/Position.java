package com.peng.animlibrary.common;

import android.graphics.PointF;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextSurface;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 19:32
 * desc   :
 */
public class Position {

    /**
     * 对齐方式
     */
    private int align;

    /**
     * 对齐于哪一个文本
     */
    private Text alignText;

    /**
     * 当前 Text 对象的位置
     */
    private PointF point = new PointF();

    /**
     * 当前 Text 在 x、y 方向移动的距离
     */
    private float translationX;
    private float translationY;

    public Position() {
        point = new PointF();
    }

    public Position(Position src) {
        align = src.align;
        alignText = src.alignText;
        point.set(src.point);
        translationX = src.translationX;
        translationY = src.translationY;
    }

    public Position(PointF point) {
        this.point = point;
    }

    public Position(int align) {
        this.align = align;
    }

    public Position(int align, Text alignText) {
        this.align = align;
        this.alignText = alignText;
    }

    public void set(Position src) {
        align = src.align;
        alignText = src.alignText;
        point.set(src.point);
        translationX = src.translationX;
        translationY = src.translationY;
    }

    public PointF getPoint() {
        return point;
    }

    /**
     * 获取当前 Text 的相对于中心点在 x 方向的距离
     * @param pivot
     * @param text
     * @param global
     * @return
     */
    public float getRelativeX(int pivot, Text text, boolean global) {
        float result = 0;

        if ((pivot & Pivot.LEFT) == Pivot.LEFT) {
//			result = point.x;
        } else if ((pivot & Pivot.RIGHT) == Pivot.RIGHT) {
            result += text.getWidth();
        } else if ((pivot & Pivot.CENTER) == Pivot.CENTER) {
            result += text.getWidth() / 2;
        }

        return global ? (result + point.x + translationX) : result;
    }

    /**
     * 获取当前 Text 的相对于中心点在 y 方向的距离
     * @param pivot
     * @param text
     * @param global
     * @return
     */
    public float getRelativeY(int pivot, Text text, boolean global) {
        float result = 0;
        if ((pivot & Pivot.BOTTOM) == Pivot.BOTTOM) {
            //result = text.getY(textSurface);
        } else if ((pivot & Pivot.TOP) == Pivot.TOP) {
            result -= text.getHeight();
        } else if ((pivot & Pivot.CENTER) == Pivot.CENTER) {
            result -= text.getHeight() / 2;
        }

        return global ? (result + point.y + translationY) : result;
    }

    /**
     * 获取当前 Text 在画布中的 x 坐标
     * @param textSurface
     * @param textWidth
     * @return
     */
    public float getX(TextSurface textSurface, float textWidth) {
        if (isAligned()) {
            if (alignedWith(Align.SURFACE_CENTER)) {// text 于画布居中
                point.x = -textWidth / 2;
            } else if (alignedWith(Align.RIGHT_OF)) {
                point.x = alignText.getX(textSurface) + alignText.getWidth();
            } else if (alignedWith(Align.LEFT_OF)) {
                point.x = alignText.getX(textSurface) - textWidth;
            } else if (alignedWith(Align.CENTER_OF)) {
                point.x = alignText.getX(textSurface) + (alignText.getWidth() - textWidth) / 2;
            } else {
                point.x = alignText.getX(textSurface);
            }
        }

        return point.x + translationX;
    }

    private boolean alignedWith(int align) {
        return (this.align & align) == align;
    }

    public float getY(TextSurface textSurface, float textHeight) {

        if (isAligned()) {
            if (alignedWith(Align.SURFACE_CENTER)) {
                point.y = textHeight / 2;
            } else if (alignedWith(Align.TOP_OF)) {
                point.y = alignText.getY(textSurface) - alignText.getHeight();
            } else if (alignedWith(Align.BOTTOM_OF)) {
                point.y = alignText.getY(textSurface) + textHeight;
            } else if (alignedWith(Align.CENTER_OF)) {
                point.y = alignText.getY(textSurface) - (alignText.getHeight() - textHeight) / 2;
            } else {
                point.y = alignText.getY(textSurface);
            }
        }

        return point.y + translationY;
    }

    public boolean isAligned() {
        return align != 0;
    }

    public void setY(float y) {
        if (point != null) point.y = y;
    }

    public void setX(float x) {
        if (point != null) point.x = x;
    }

    public void setTranslationX(float x) {
        translationX = x;
    }

    public void setTranslationY(float y) {
        translationY = y;
    }

    public void onAnimationEnd() {

    }
}

