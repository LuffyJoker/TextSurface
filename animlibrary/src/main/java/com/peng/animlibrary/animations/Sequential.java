package com.peng.animlibrary.animations;

import com.peng.animlibrary.common.TYPE;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:25
 * desc   :
 */
public class Sequential extends AnimationsSet {
    public Sequential(ISurfaceAnimation... animations) {
        super(TYPE.SEQUENTIAL, animations);
    }
}
