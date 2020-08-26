package com.peng.animlibrary.animations;

import android.util.Log;

import androidx.annotation.NonNull;

import com.peng.animlibrary.Debug;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.common.TYPE;
import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.interfaces.ISet;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 21:04
 * desc   :
 */
public class AnimationsSet implements IEndListener, ISet {

    private LinkedList<ISurfaceAnimation> animations;
    private TYPE type;
    private ISurfaceAnimation currentAnimation;
    private ISurfaceAnimation lastAnimation;//type == PARALLEL
    private int index;
    private IEndListener listener;
    private boolean canceled;
    private static final DurationComparator DURATION_COMPARATOR = new DurationComparator();

    public AnimationsSet(TYPE type, ISurfaceAnimation... animations) {
        this.type = type;
        this.animations = new LinkedList<>(Arrays.asList(animations));
    }

    @Override
    public void onAnimationEnd(ISurfaceAnimation animation) {
        if (Debug.ENABLED) {
            Log.i("endAnimation", animation.toString());
        }

        if (canceled) {
            finalizeAnimation();
            return;
        }

        if (type == TYPE.SEQUENTIAL) {
            if (index < animations.size()) {
                setCurrentAnimation(animations.get(index++));
            } else {
                finalizeAnimation();
            }
        } else {
            if (animation == lastAnimation) finalizeAnimation();
        }
    }

    private void finalizeAnimation() {
        if (listener != null) listener.onAnimationEnd(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void start(IEndListener listener) {
        this.listener = listener;
        index = 0;
        canceled = false;
        if (type == TYPE.SEQUENTIAL) {
            setCurrentAnimation(animations.get(index++));
        } else if (type == TYPE.PARALLEL) {
            Collections.sort(animations, DURATION_COMPARATOR);
            lastAnimation = animations.getLast();
            for (ISurfaceAnimation animation : animations) {
                setCurrentAnimation(animation);
            }
        }
    }

    private void setCurrentAnimation(ISurfaceAnimation animation) {
        if (Debug.ENABLED) Log.i("startedAnimation", animation.toString());
        currentAnimation = animation;
        currentAnimation.onStart();
        currentAnimation.start(this);
    }

    @Override
    public TYPE getType() {
        return type;
    }


    @Override
    public void setTextSurface(@NonNull TextSurface textSurface) {
        for (ISurfaceAnimation animation : animations) animation.setTextSurface(textSurface);
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public void cancel() {
        canceled = true;
        if (type == TYPE.SEQUENTIAL) {
            if (currentAnimation != null) {
                currentAnimation.cancel();
            }
        } else {
            for (ISurfaceAnimation animation : animations) animation.cancel();
        }
    }

    @Override
    public LinkedList<ISurfaceAnimation> getAnimations() {
        return animations;
    }

    private static class DurationComparator implements Comparator<ISurfaceAnimation> {
        @Override
        public int compare(ISurfaceAnimation lhs, ISurfaceAnimation rhs) {
            return (int) (lhs.getDuration() - rhs.getDuration());
        }
    }

}

