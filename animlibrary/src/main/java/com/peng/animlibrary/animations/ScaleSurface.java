package com.peng.animlibrary.animations;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.peng.animlibrary.SurfaceCamera;
import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.common.Pivot;
import com.peng.animlibrary.interfaces.ICameraAnimation;
import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.utils.Utils;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:25
 * desc   :
 */
public class ScaleSurface implements ICameraAnimation, ValueAnimator.AnimatorUpdateListener {

    private SurfaceCamera camera;
    private TextSurface textSurface;
    private int duration;
    private final Text textPivot;
    private int fit = -1;
    private int pivot;
    private float toScale;
    private ObjectAnimator animator;

    public ScaleSurface(int duration, Text textPivot, int pivot, float toScale) {
        this.duration = duration;
        this.textPivot = textPivot;
        this.pivot = pivot;
        this.toScale = toScale;
    }

    public ScaleSurface(int duration, Text textPivot, int fit) {
        this.duration = duration;
        this.textPivot = textPivot;
        this.fit = fit;
    }

    @Override public void setCamera(SurfaceCamera camera) {
        this.camera = camera;
    }

    @Override public void onStart() {

    }

    @Override public void start(@Nullable IEndListener listener) {

        float pivotX;
        float pivotY;

        if (fit == -1) {
            pivotX = textPivot.getPosition().getRelativeX(pivot, textPivot, true);
            pivotY = textPivot.getPosition().getRelativeY(pivot, textPivot, true);
        } else {
            int surfaceWidth = textSurface.getWidth();
            float textWidth = textPivot.getWidth();
            toScale = surfaceWidth / textWidth;
            pivotX = textPivot.getPosition().getRelativeX(Pivot.CENTER, textPivot, true);
            pivotY = textPivot.getPosition().getRelativeY(Pivot.CENTER, textPivot, true);
        }

        PropertyValuesHolder scaleHolder = PropertyValuesHolder.ofFloat("scale", camera.getScale(), toScale);
        PropertyValuesHolder pivotXHolder = PropertyValuesHolder.ofFloat("scalePivotX", camera.getScalePivotX(), pivotX);
        PropertyValuesHolder pivotYHolder = PropertyValuesHolder.ofFloat("scalePivotY", camera.getScalePivotY(), pivotY);

        animator = ObjectAnimator.ofPropertyValuesHolder(camera, scaleHolder, pivotXHolder, pivotYHolder);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.setDuration(duration);
        animator.addUpdateListener(this);
        Utils.addEndListener(this, animator, listener);
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
        }
    }

    @Override public void onAnimationUpdate(ValueAnimator animation) {
        textSurface.invalidate();
    }

    @Override public String toString() {
        return "ScaleSurface{" +
                "textPivot=" + ((textPivot == null) ? "null" : textPivot.toString()) +
                '}';
    }
}
