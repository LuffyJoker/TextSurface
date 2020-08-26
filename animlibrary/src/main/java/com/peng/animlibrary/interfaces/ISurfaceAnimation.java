package com.peng.animlibrary.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.peng.animlibrary.TextSurface;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 20:32
 * desc   : surface 的动画执行标准
 */
public interface ISurfaceAnimation {

    void onStart();

    void start(@Nullable IEndListener listener);

    void setTextSurface(@NonNull TextSurface textSurface);

    long getDuration();

    void cancel();

}
