package com.peng.animlibrary.interfaces;

import androidx.annotation.NonNull;

import com.peng.animlibrary.Text;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 22:50
 * desc   :
 */
public interface ITextSurfaceAnimation extends ISurfaceAnimation {

    void setInitValues(@NonNull Text text);

    Text getText();

}
