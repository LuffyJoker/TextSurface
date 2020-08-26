package com.peng.textanimsurface.sample;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextBuilder;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.animations.AnimationsSet;
import com.peng.animlibrary.animations.Rotate3D;
import com.peng.animlibrary.common.Align;
import com.peng.animlibrary.common.Axis;
import com.peng.animlibrary.common.Direction;
import com.peng.animlibrary.common.Pivot;
import com.peng.animlibrary.common.TYPE;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:47
 * desc   :
 */
public class Rotation3DSample {
    public static void play(TextSurface textSurface) {
        Text textA = TextBuilder.create("How are you?").setPosition(Align.SURFACE_CENTER).build();
        Text textB = TextBuilder.create("I'm fine! And you?").setPosition(Align.SURFACE_CENTER, textA).build();
        Text textC = TextBuilder.create("Haaay!").setPosition(Align.SURFACE_CENTER, textB).build();
        int duration = 2750;

        textSurface.play(TYPE.SEQUENTIAL,
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromCenter(textA, duration, Direction.CLOCK, Axis.X),
                        Rotate3D.hideFromCenter(textA, duration, Direction.CLOCK, Axis.Y)
                ),
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromSide(textB, duration, Pivot.LEFT),
                        Rotate3D.hideFromSide(textB, duration, Pivot.RIGHT)
                ),
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromSide(textC, duration, Pivot.TOP),
                        Rotate3D.hideFromSide(textC, duration, Pivot.BOTTOM)
                )
        );
    }
}

