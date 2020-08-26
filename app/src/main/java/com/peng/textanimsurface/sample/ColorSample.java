package com.peng.textanimsurface.sample;

import android.graphics.Color;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextBuilder;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.animations.Alpha;
import com.peng.animlibrary.animations.ChangeColor;
import com.peng.animlibrary.common.Align;
import com.peng.animlibrary.common.TYPE;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:46
 * desc   :
 */
public class ColorSample {
    public static void play(TextSurface textSurface) {

        Text textA = TextBuilder
                .create("Now why you loer en kyk gelyk?")
                .setPosition(Align.SURFACE_CENTER)
                .setSize(100)
                .setAlpha(0)
                .build();

        textSurface.play(TYPE.SEQUENTIAL,
                Alpha.show(textA, 1000),
                ChangeColor.to(textA, 1000, Color.RED),
                ChangeColor.fromTo(textA, 1000, Color.BLUE, Color.CYAN),
                Alpha.hide(textA, 1000)
        );
    }
}
