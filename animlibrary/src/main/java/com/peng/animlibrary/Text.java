package com.peng.animlibrary;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.peng.animlibrary.common.Position;
import com.peng.animlibrary.common.ScaleValue;
import com.peng.animlibrary.interfaces.ITextEffect;

import java.util.ArrayList;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 19:31
 * desc   :
 */
public class Text implements Comparable<Text> {

    private Paint paint;
    private final String text;
    private RectF defaultSize;
    private RectF currentSize = new RectF();
    private Position position;
    private RectF padding;
    private int index;
    private ScaleValue scale = new ScaleValue();
    private Matrix matrix = new Matrix();
    private ArrayList<ITextEffect> effects = new ArrayList<>();
    private float dx;
    private float fontDescent;

    public Text(String text, Position position, RectF padding, Paint paint) {
        this.text = text;
        this.position = position;
        this.padding = padding;
        this.paint = paint;

        initBounds(text);
    }

    public void addEffect(ITextEffect effect) {
        effects.add(effect);
    }

    public float getFontDescent() {
        return fontDescent;
    }

    private void initBounds(String text) {
        String trimmed = text.trim();
        if (trimmed.length() < text.length()) {

            int length = text.length();
            int start = text.lastIndexOf(trimmed);
            int end = length - (start + trimmed.length());

            text = Utils.genString(start) + text + Utils.genString(end);
        }

        Rect tmp = new Rect();
        paint.getTextBounds(text, 0, text.length(), tmp);

        fontDescent = paint.getFontMetrics().descent;
        defaultSize = new RectF(tmp);
        // text 的测量宽度与 通过 paint 获取的 rect 宽度之差，即实际宽度与绘制宽度的偏移量
        dx = paint.measureText(text) - tmp.width();
        defaultSize.left = 0;
        defaultSize.top = -paint.getFontSpacing();
        defaultSize.right = tmp.width() + dx;
        defaultSize.bottom = 0;
        defaultSize.set(defaultSize.left, defaultSize.top, defaultSize.right, defaultSize.bottom);
        currentSize.set(defaultSize.left, defaultSize.top, defaultSize.right, defaultSize.bottom);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /**
     * 绘制 Text 的内容
     *
     * @param canvas
     * @param textSurface
     */
    public void onDraw(Canvas canvas, TextSurface textSurface) {

        layout(textSurface);

        canvas.save();
        canvas.concat(matrix);

        final float finalX = padding.left;

        if (effects.isEmpty()) {
            canvas.drawText(text, finalX, -padding.bottom - fontDescent, paint);
        } else {
            for (ITextEffect effect : effects) {
                canvas.save();
                effect.apply(canvas, text, finalX, -padding.bottom, paint);
                canvas.drawText(text, finalX, -padding.bottom, paint);
                canvas.restore();
            }
        }

        canvas.restore();

        if (Debug.ENABLED) {
            canvas.drawRect(
                    currentSize.left,
                    currentSize.top - padding.bottom - padding.top,
                    currentSize.right + padding.left + padding.right,
                    currentSize.bottom,
                    Debug.RED_STROKE
            );
        }
    }

    void layout(TextSurface textSurface) {

        currentSize.set(defaultSize.left, defaultSize.top, defaultSize.right, defaultSize.bottom);

        // 获取缩放比例
        float sx = scale.getScaleX();
        float sy = scale.getScaleY();

        // 获取相对中心点的偏移量
        float sPivotX = position.getRelativeX((int) scale.getPivot().x, this, false);
        float sPivotY = position.getRelativeY((int) scale.getPivot().y, this, false);
        float x = position.getX(textSurface, getWidth() * sx);
        float y = position.getY(textSurface, getHeight() * sy);

        matrix.reset();
        // 在变换之前移动内容，换言之就是设置旋旋转中心
        matrix.preTranslate(x, y);
        // 设置缩放的属性
        matrix.preScale(sx, sy, sPivotX, sPivotY);
        // 将 matrix 的值映射到 currentSize 中
        matrix.mapRect(currentSize);
    }

    public float getY(TextSurface textSurface) {
        return position.getY(textSurface, getHeight());
    }

    public float getX(TextSurface textSurface) {
        return position.getX(textSurface, getWidth());
    }

    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    public RectF bounds() {
        return defaultSize;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setScaleX(float value) {
        scale.setValueX(value);
    }

    public void setScaleY(float value) {
        scale.setValueY(value);
    }

    public void setScalePivot(float x, float y) {
        scale.getPivot().set(x, y);
    }

    public float getScaleY() {
        return scale.getScaleY();
    }

    public void setTranslationX(float value) {
        position.setTranslationX(value);
    }

    public void setTranslationY(float value) {
        position.setTranslationY(value);
    }

    @Override
    public int compareTo(@NonNull Text another) {
        return text.compareTo(another.text);
    }

    public float getWidth() {
        return (currentSize.width() + padding.left + padding.right);
    }

    public float getHeight() {
        return (currentSize.height() + padding.top + padding.bottom);
    }

    public Position getPosition() {
        return position;
    }

    public void onAnimationEnd() {
        position.onAnimationEnd();
    }

    public float getScaleX() {
        return scale.getScaleX();
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                '}';
    }

    public void removeEffect(ITextEffect effect) {
        effects.remove(effect);
    }

    public String getValue() {
        return text;
    }

    public Paint getPaint() {
        return paint;
    }
}
