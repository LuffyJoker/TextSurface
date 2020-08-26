package com.peng.textanimsurface.sample;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextBuilder;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.animations.Alpha;
import com.peng.animlibrary.animations.AnimationsSet;
import com.peng.animlibrary.animations.TransSurface;
import com.peng.animlibrary.common.Align;
import com.peng.animlibrary.common.Pivot;
import com.peng.animlibrary.common.TYPE;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:48
 * desc   :
 */
public class SurfaceTransSample {
    public static void play(TextSurface textSurface) {
        Text textA = TextBuilder.create("TextA").setPosition(Align.SURFACE_CENTER).build();
        Text textB = TextBuilder.create("TextB").setPosition(Align.RIGHT_OF | Align.BOTTOM_OF, textA).build();
        Text textC = TextBuilder.create("TextC").setPosition(Align.LEFT_OF | Align.BOTTOM_OF, textB).build();
        Text textD = TextBuilder.create("TextD").setPosition(Align.RIGHT_OF | Align.BOTTOM_OF, textC).build();

        int duration = 500;

        textSurface.play(TYPE.SEQUENTIAL,
                Alpha.show(textA, duration),
                new AnimationsSet(TYPE.PARALLEL, Alpha.show(textB, duration), new TransSurface(duration, textB, Pivot.CENTER)),
                new AnimationsSet(TYPE.PARALLEL, Alpha.show(textC, duration), new TransSurface(duration, textC, Pivot.CENTER)),
                new AnimationsSet(TYPE.PARALLEL, Alpha.show(textD, duration), new TransSurface(duration, textD, Pivot.CENTER)),
                new TransSurface(duration, textC, Pivot.CENTER),
                new TransSurface(duration, textB, Pivot.CENTER),
                new TransSurface(duration, textA, Pivot.CENTER)
        );
    }
}
