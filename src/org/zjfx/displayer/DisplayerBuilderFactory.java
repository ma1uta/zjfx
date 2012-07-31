package org.zjfx.displayer;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.zjfx.ZModel;

public class DisplayerBuilderFactory implements BuilderFactory {

    public static final String ATTR = "attr";
    public static final String MODEL = "model";
    public static final String CLASS = "class";
    private final JavaFXBuilderFactory defaultBuilderFactory;
    private ZModel<?> model;

    public DisplayerBuilderFactory(ZModel<?> model) {
        defaultBuilderFactory = new JavaFXBuilderFactory();
        this.model = model;
    }

    @Override
    public Builder<?> getBuilder(Class<?> aClass) {
        Builder<?> builder;
        if (AbstractDisplayer.class.isAssignableFrom(aClass)) {
            DisplayerBuilder displayerBuilder = new DisplayerBuilder();
            displayerBuilder.put(CLASS, aClass);
            displayerBuilder.put(MODEL, model);
            builder = displayerBuilder;
        } else
            builder = defaultBuilderFactory.getBuilder(aClass);
        return builder;
    }
}
