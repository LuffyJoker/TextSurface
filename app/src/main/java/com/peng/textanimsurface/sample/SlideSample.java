package com.peng.textanimsurface.sample;

import com.peng.animlibrary.Text;
import com.peng.animlibrary.TextBuilder;
import com.peng.animlibrary.TextSurface;
import com.peng.animlibrary.animations.AnimationsSet;
import com.peng.animlibrary.animations.Slide;
import com.peng.animlibrary.animations.TransSurface;
import com.peng.animlibrary.common.Align;
import com.peng.animlibrary.common.Pivot;
import com.peng.animlibrary.common.Side;
import com.peng.animlibrary.common.TYPE;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/8/26 20:48
 * desc   :
 */
public class SlideSample {
    public static void play(TextSurface textSurface) {

        Text textA = TextBuilder.create(" How are you?").build();
        Text textB = TextBuilder.create("I'm fine! ").setPosition(Align.LEFT_OF, textA).build();
        Text textC = TextBuilder.create("Are you sure?").setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textB).build();
        Text textD = TextBuilder.create("Totally!").setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textC).build();
        int duration = 1250;

        textSurface.play(
                TYPE.SEQUENTIAL,
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.SEQUENTIAL,
                                new AnimationsSet(TYPE.PARALLEL, Slide.showFrom(Side.LEFT, textA, duration), Slide.showFrom(Side.RIGHT, textB, duration)),
                                Slide.showFrom(Side.TOP, textC, duration),
                                Slide.showFrom(Side.BOTTOM, textD, duration)
                        ),
                        new TransSurface(duration * 3, textD, Pivot.CENTER)
                ),
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.SEQUENTIAL,
                                new AnimationsSet(TYPE.PARALLEL, Slide.hideFrom(Side.LEFT, textD, duration), Slide.hideFrom(Side.RIGHT, textC, duration)),
                                Slide.hideFrom(Side.TOP, textB, duration),
                                Slide.hideFrom(Side.BOTTOM, textA, duration)
                        ),
                        new TransSurface(duration * 3, textA, Pivot.CENTER)
                )

        );
    }
}

