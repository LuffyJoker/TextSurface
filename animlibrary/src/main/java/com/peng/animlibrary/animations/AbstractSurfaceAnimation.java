package com.peng.animlibrary.animations;

import android.animation.ValueAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.interfaces.ITextSurfaceAnimation;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:22
 * desc   :
 */
public class AbstractSurfaceAnimation implements ITextSurfaceAnimation, ValueAnimator.AnimatorUpdateListener {

    protected final Text text;
    protected final int duration;
    protected TextSurface textSurface;

    public AbstractSurfaceAnimation(Text text, int duration) {
        this.text = text;
        this.duration = duration;
    }

    @Override public void setInitValues(@NonNull Text text) {

    }

    @NonNull @Override public Text getText() {
        return text;
    }

    @Override public void onStart() {

    }

    @Override public void start(final @Nullable IEndListener listener) {

    }

    @Override public void setTextSurface(@NonNull TextSurface textSurface) {
        this.textSurface = textSurface;
    }

    @Override public long getDuration() {
        return duration;
    }

    @Override public void cancel() {

    }

    @Override public void onAnimationUpdate(ValueAnimator animation) {
        textSurface.invalidate();
    }
}

