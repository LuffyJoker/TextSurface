package com.peng.animlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.peng.animlibrary.animations.AnimationsSet;
import com.peng.animlibrary.common.TYPE;
import com.peng.animlibrary.interfaces.ICameraAnimation;
import com.peng.animlibrary.interfaces.ISet;
import com.peng.animlibrary.interfaces.ISurfaceAnimation;
import com.peng.animlibrary.interfaces.ITextSurfaceAnimation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * author : Mr.Q
 * e-mail : m1838044925
 * date   : 2020/7/29 20:29
 * desc   :
 */
public class TextSurface extends FrameLayout {

    private TreeSet<Text> textsTree = new TreeSet<>();
    private SurfaceCamera camera = new SurfaceCamera();
    private ISurfaceAnimation currentAnimation = null;

    public TextSurface(Context context) {
        super(context);
        config();
    }

    public TextSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        config();
    }

    private void config() {
        // 处于性能考虑，默认不会执行绘制，setWillNotDraw(false);目的是为了让其绘制出来
        setWillNotDraw(false);
    }

    public SurfaceCamera getCamera() {
        return camera;
    }

    public void play(TYPE type, ISurfaceAnimation... animations) {
        play(new AnimationsSet(type, animations));
    }

    public void play(ISurfaceAnimation... animations) {
        play(new AnimationsSet(TYPE.PARALLEL, animations));
    }

    public void play(ISurfaceAnimation animation) {
        configAnimations(animation);
        animation.setTextSurface(this);
        layout();
        currentAnimation = animation;
        currentAnimation.start(null);
    }

    private void layout() {
        Iterator<Text> iterator = textsTree.iterator();
        while (iterator.hasNext()) {
            iterator.next().layout(this);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void configAnimations(ISurfaceAnimation animation) {
        if (animation instanceof ICameraAnimation) {
            ((ICameraAnimation) animation).setCamera(camera);
        } else if (animation instanceof ISet) {
            LinkedList<ISurfaceAnimation> animations = ((ISet) animation).getAnimations();
            for (ISurfaceAnimation a : animations) {
                configAnimations(a);
            }
        } else if (animation instanceof ITextSurfaceAnimation) {
            ITextSurfaceAnimation textAnimation = (ITextSurfaceAnimation) animation;
            Text text = textAnimation.getText();
            if (text != null && textsTree.add(text)) {
                textAnimation.setInitValues(text);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        camera.onDraw(canvas);
        for (Text text : textsTree) text.onDraw(canvas, this);
    }

    public void reset() {
        if (currentAnimation != null) {
            currentAnimation.cancel();
            currentAnimation = null;
        }
        textsTree.clear();
        camera.reset();
        invalidate();
    }
}

