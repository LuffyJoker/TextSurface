package com.peng.animlibrary.animations;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;
import com.peng.animlibrary.interfaces.ITextEffect;
import com.peng.animlibrary.utils.Utils;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:25
 * desc   :
 */
public class ShapeReveal implements ITextEffect, ValueAnimator.AnimatorUpdateListener {

    private final Text text;
    private final int duration;
    private TextSurface textSurface;
    private IRevealShape revealShape;
    private boolean hideOnEnd;
    private ValueAnimator animator;

    public static ShapeReveal create(Text text, int duration, IRevealShape revealShape, boolean hideOnEnd) {
        return new ShapeReveal(text, duration, revealShape, hideOnEnd);
    }

    private ShapeReveal(Text text, int duration, IRevealShape revealShape, boolean hideOnEnd) {
        this.text = text;
        this.duration = duration;
        this.revealShape = revealShape;
        this.hideOnEnd = hideOnEnd;
        revealShape.setText(text);
    }

    @Override public void apply(Canvas canvas, String textValue, float x, float y, Paint paint) {
        revealShape.clip(canvas, textValue, x, y, paint);
    }

    @Override public void setInitValues(@NonNull Text text) {
        if (revealShape.isToShow()) text.setAlpha(0);
    }

    @NonNull @Override public Text getText() {
        return text;
    }

    @Override public void onStart() {
        text.addEffect(this);
    }

    @Override public void start(@Nullable final IEndListener listener) {

        text.setAlpha(255);
        animator = revealShape.getAnimator();
        animator.setInterpolator(new FastOutSlowInInterpolator());
        Utils.addEndListener(this, animator, new IEndListener() {
            @Override public void onAnimationEnd(ISurfaceAnimation animation) {
                text.removeEffect(ShapeReveal.this);
                if (hideOnEnd) text.setAlpha(0);
                if (listener != null) listener.onAnimationEnd(ShapeReveal.this);
            }
        });
        animator.setDuration(duration);
        animator.start();

    }

    @Override public void setTextSurface(@NonNull TextSurface textSurface) {
        revealShape.setTextSurface(textSurface);
        this.textSurface = textSurface;
    }

    @Override public long getDuration() {
        return 0;
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

    public interface IRevealShape {
        void setText(Text text);

        void setTextSurface(TextSurface textSurface);

        ValueAnimator getAnimator();

        void clip(Canvas canvas, String textValue, float x, float y, Paint paint);

        boolean isToShow();
    }
}

