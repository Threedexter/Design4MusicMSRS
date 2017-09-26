package com.mock;

import com.back.Debugger;
import com.back.EffectContent;
import com.back.IAlertContent;
import com.back.MemoryGestureHolder;

import java.util.ArrayList;
import java.util.List;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.Gesture;

/**
 * Created by Rowan on 26/09/17.
 */

public abstract class Data {
    private static List<IAlertContent> effects = new ArrayList<>();

    private static void called() {
        if (!Debugger.isLogging())
            Debugger.setLogging(true);
    }

    public static List<IAlertContent> getEffects() {
        called();

        if (effects.isEmpty()) {
            Delegate<EffectContent> trigger = new Delegate<EffectContent>() {
                @Override
                public void invoke(EffectContent obj) {
                    Debugger.log(obj.getOptionName() + " : " + obj.getId());
                }
            };
            EffectContent a = new EffectContent("LEDLamp1 - Horizontal", trigger);
            EffectContent b = new EffectContent("LEDLamp1 - Vertical", trigger);
            EffectContent c = new EffectContent("LEDLamp1 - Shutter", trigger);
            EffectContent d = new EffectContent("LEDLamp1 - Red", trigger);
            EffectContent e = new EffectContent("LEDLamp1 - Green", trigger);
            EffectContent f = new EffectContent("LEDLamp1 - Blue", trigger);

            EffectContent a2 = new EffectContent("LEDLamp2 - Horizontal", trigger);
            EffectContent b2 = new EffectContent("LEDLamp2 - Vertical", trigger);
            EffectContent c2 = new EffectContent("LEDLamp2 - Shutter", trigger);
            EffectContent d2 = new EffectContent("LEDLamp2 - Red", trigger);
            EffectContent e2 = new EffectContent("LEDLamp2 - Green", trigger);
            EffectContent f2 = new EffectContent("LEDLamp2 - Blue", trigger);
            effects.add(a);
            effects.add(b);
            effects.add(c);
            effects.add(d);
            effects.add(e);
            effects.add(f);

            effects.add(a2);
            effects.add(b2);
            effects.add(c2);
            effects.add(d2);
            effects.add(e2);
            effects.add(f2);
        }
        return effects;
    }

    public static List<Gesture> getGestures() {
        called();
        
        return MemoryGestureHolder.getGestures();
    }
}
