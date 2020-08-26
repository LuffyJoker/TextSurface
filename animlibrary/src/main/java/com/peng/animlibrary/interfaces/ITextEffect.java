package com.peng.animlibrary.interfaces;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/2 15:07
 * desc   :
 */
public interface ITextEffect extends ITextSurfaceAnimation {
    void apply(Canvas canvas, String textValue, float x, float y, Paint paint);
}
