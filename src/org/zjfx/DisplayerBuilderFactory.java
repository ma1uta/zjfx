package org.zjfx;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.zjfx.displayer.DisplayerBuilder;
import org.zjfx.displayer.StringDisplayer;

import java.util.HashSet;
import java.util.Set;

public class DisplayerBuilderFactory implements BuilderFactory {

    public static final String ATTR = "attr";
    public static final String MODEL = "model";
    public static final String CLASS = "class";
    private final JavaFXBuilderFactory defaultBuilderFactory;
    private Set<Class<?>> builders;
    private ZModel<?> model;

    {
        builders = new HashSet<Class<?>>();
        builders.add(StringDisplayer.class);
    }

    public DisplayerBuilderFactory(ZModel<?> model) {
        defaultBuilderFactory = new JavaFXBuilderFactory();
        this.model = model;
    }

    @Override
    public Builder<?> getBuilder(Class<?> aClass) {
        Builder<?> builder;
        if (builders.contains(aClass)) {
            DisplayerBuilder displayerBuilder = new DisplayerBuilder();
            displayerBuilder.put(CLASS, aClass);
            displayerBuilder.put(MODEL, model);
            builder = displayerBuilder;
        } else
            builder = defaultBuilderFactory.getBuilder(aClass);
        return builder;
    }
}
