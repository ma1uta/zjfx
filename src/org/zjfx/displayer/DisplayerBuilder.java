package org.zjfx.displayer;

import org.zjfx.ZModel;
import javafx.util.Builder;

import java.util.HashMap;

public class DisplayerBuilder extends HashMap<String, Object> implements Builder<AbstractDisplayer> {

    @Override
    public AbstractDisplayer build() {
        Class<? extends AbstractDisplayer> clazz = (Class<? extends AbstractDisplayer>) get(DisplayerBuilderFactory.CLASS);
        AbstractDisplayer displayer;
        try {
            displayer = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot find displayer by class " + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot find displayer by class " + clazz.getName(), e);
        }
        displayer.setAttr((String) get(DisplayerBuilderFactory.ATTR));
        displayer.setModel((ZModel<?>) get(DisplayerBuilderFactory.MODEL));
        displayer.init();
        return displayer;
    }
}
