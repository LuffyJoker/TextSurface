package com.peng.textanimsurface.sample;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextBuilder;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.animations.Alpha;
import com.peng.animlibrary.animations.AnimationsSet;
import com.peng.animlibrary.animations.Delay;
import com.peng.animlibrary.animations.ScaleSurface;
import com.peng.animlibrary.common.Align;
import com.peng.animlibrary.common.Fit;
import com.peng.animlibrary.common.TYPE;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:48
 * desc   :
 */
public class SurfaceScaleSample {
    public static void play(TextSurface textSurface) {

        Text textA = TextBuilder.create("How are you?").setPosition(Align.SURFACE_CENTER).build();
        Text textB = TextBuilder.create("Would you mind?").setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textA).build();
        Text textC = TextBuilder.create("Yes!").setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textB).build();


        textSurface.play(TYPE.SEQUENTIAL,
                Alpha.show(textA, 500),
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.PARALLEL, Alpha.show(textB, 500), Alpha.hide(textA, 500)),
                        new ScaleSurface(500, textB, Fit.WIDTH)
                ),
                Delay.duration(1000),
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.PARALLEL, Alpha.show(textC, 500), Alpha.hide(textB, 500)),
                        new ScaleSurface(500, textC, Fit.WIDTH)
                )
        );
    }
}
