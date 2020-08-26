package com.peng.animlibrary.animations;

import androidx.annotation.Nullable;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.common.TYPE;
import com.peng.animlibrary.interfaces.IEndListener;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:23
 * desc   :
 */
public class Just extends AbstractSurfaceAnimation {

    public static AnimationsSet show(Text... texts) {
        Just[] justs = new Just[texts.length];
        for (int i = 0; i < texts.length; i++) justs[i] = Just.show(texts[i]);
        return new AnimationsSet(TYPE.PARALLEL, justs);
    }

    public static Just show(Text text) {
        return new Just(text);
    }

    private Just(Text text) {
        super(text, 0);
    }

    @Override public void start(@Nullable IEndListener listener) {
        textSurface.invalidate();
        if (listener != null) listener.onAnimationEnd(this);
    }
}

