package com.back;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;

/**
 * Created by Rowan on 26/09/17.
 */

public class EffectContent implements IAlertContent {

    /**
     * REQUIRES:
     *  - class to be in an activity or with access to a general context
     * How to use:
     *
        Delegate<EffectContent> trigger = new Delegate<EffectContent>() {
        @Override
        public void invoke(EffectContent obj) {
            Debugger.log(obj.getOptionName() + " : " + obj.getId());
        }
        };
         EffectContent a = new EffectContent("LEDLamp1 - Horizontal", trigger);

         List<IAlertContent> effects = new ArrayList<>();
         effects.add(a);

         Navigator.createAlertDialog(this, "Connect effect", effects);
     */

    private String name;
    private DelegateRegister<EffectContent> del = new DelegateRegister();

    public EffectContent(String name, Delegate<EffectContent> trigger) {
        this.name = name;
        del.attachDelegate(trigger);
    }

    @Override
    public void trigger() {
        del.invokeAll(this);
    }

    @Override
    public String getId() {
        return String.valueOf(getName().hashCode());
    }

    @Override
    public String getOptionName() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
