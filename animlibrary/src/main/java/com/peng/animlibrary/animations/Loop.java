package com.peng.animlibrary.animations;

import com.peng.animlibrary.interfaces.IEndListener;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:24
 * desc   :
 */
public class Loop extends Sequential {

    private final IEndListener restartListener = new IEndListener() {
        @Override public void onAnimationEnd(ISurfaceAnimation animation) {
            if (canceled) {
                if (endListener != null) endListener.onAnimationEnd(Loop.this);
            } else {
                Loop.super.start(restartListener);
            }
        }
    };

    private IEndListener endListener;
    private boolean canceled;

    public Loop(ISurfaceAnimation... animations) {
        super(animations);
    }

    @Override public void start(IEndListener listener) {
        endListener = listener;
        canceled = false;
        super.start(restartListener);
    }

    @Override public void cancel() {
        canceled = true;
        super.cancel();
    }
}

