package com.peng.animlibrary.animations;

import com.peng.animlibrary.common.TYPE;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:24
 * desc   :
 */
public class Parallel extends AnimationsSet {
    public Parallel(ISurfaceAnimation... animations) {
        super(TYPE.PARALLEL, animations);
    }
}
