package com.peng.animlibrary.animations;

import androidx.annotation.Nullable;

import com.peng.animlibrary.interfaces.IEndListener;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:23
 * desc   :
 */
public class Delay extends AbstractSurfaceAnimation {

    public static Delay duration(int duration) {
        return new Delay(duration);
    }

    private Runnable action = null;

    public Delay(int duration) {
        super(null, duration);
    }

    @Override public void start(@Nullable final IEndListener listener) {
        action = new Runnable() {
            @Override public void run() {
                if (listener != null) listener.onAnimationEnd(Delay.this);
            }
        };
        textSurface.postDelayed(action, duration);
    }

    @Override public void cancel() {
        if (action != null) {
            textSurface.removeCallbacks(action);
            action = null;
        }
    }

    @Override public String toString() {
        return "Delay{" +
                "duration=" + duration +
                '}';
    }
}

