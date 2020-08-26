package com.peng.animlibrary.interfaces;

import com.peng.animlibrary.common.TYPE;

import java.util.LinkedList;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 21:04
 * desc   :
 */
public interface ISet extends ISurfaceAnimation {

    TYPE getType();

    LinkedList<ISurfaceAnimation> getAnimations();
}

