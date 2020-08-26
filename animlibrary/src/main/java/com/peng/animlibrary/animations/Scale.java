package com.peng.animlibrary.animations;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.interfaces.ITextSurfaceAnimation;
import com.peng.animlibrary.utils.Utils;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:24
 * desc   :
 */
public class Scale implements ITextSurfaceAnimation, ValueAnimator.AnimatorUpdateListener {

    private int duration;
    private final float from;
    private final float to;
    private final int pivot;
    private Text text;
    private TextSurface textSurface;
    private ObjectAnimator animator;

    public Scale(Text text, int duration, float from, float to, int pivot) {
        this.text = text;
        this.duration = duration;
        this.from = from;
        this.to = to;
        this.pivot = pivot;
    }

    @Override public void setInitValues(@NonNull Text text) {

    }

    @NonNull
    @Override public Text getText() {
        return text;
    }

    @Override public void onStart() {

    }

    @Override public void start(@Nullable IEndListener listener) {
        text.setScalePivot(pivot, pivot);
        PropertyValuesHolder sX = PropertyValuesHolder.ofFloat("scaleX", from, to);
        PropertyValuesHolder sY = PropertyValuesHolder.ofFloat("scaleY", from, to);
        animator = ObjectAnimator.ofPropertyValuesHolder(text, sX, sY);
        Utils.addEndListener(this, animator, listener);
        animator.setDuration(duration);
        animator.addUpdateListener(this);
        animator.start();
    }

    @Override public void setTextSurface(@NonNull TextSurface textSurface) {
        this.textSurface = textSurface;
    }

    @Override public long getDuration() {
        return duration;
    }

    @Override public void cancel() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }
    }

    @Override public void onAnimationUpdate(ValueAnimator animation) {
        textSurface.invalidate();
    }

    @Override public String toString() {
        return "Scale{" +
                "text=" + text +
                '}';
    }
}

