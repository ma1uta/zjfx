package org.zjfx;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public abstract class ModelController implements Initializable {

    private static final String SETTER_PREFIX = "set";
    private static final String PROPERTY_SUFFIX = "Property";

    protected ZModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Object obj = resourceBundle.getObject(ZResourceBundle.ZMODEL);
        if (!ZModel.class.isAssignableFrom(obj.getClass()))
            throw new IllegalStateException();
        this.model = (ZModel) obj;
        initControls();
        bindControls();
    }

    public void initControls() {
        for (Field field : getClass().getDeclaredFields()) {
            if (!field.isAccessible())
                field.setAccessible(true);
            Attribute annotation = field.getAnnotation(Attribute.class);
            String attr = annotation.modelAttribute();
            attr = (attr == null) || (attr.isEmpty()) ? getDefaultModelAttribute(field) : attr;
            String property = annotation.controlProperty();
            try {
                StringConverter converter = annotation.converter().newInstance();
                initControl((Node) field.get(this), property, attr, converter);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected String getDefaultModelAttribute(Field field) {
        return field.getName();
    }

    public void initControl(Node control, String controlProperty, String modelProperty, StringConverter converter) {
        String methodName = SETTER_PREFIX + capitalize(controlProperty);
        String value = converter.toString(model.getProperty(modelProperty).getValue());
        try {
            control.getClass().getMethod(methodName, String.class).invoke(control, value);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void bindControls() {
        for (Field field : getClass().getDeclaredFields()) {
            if (!field.isAccessible())
                field.setAccessible(true);
            Attribute annotation = field.getAnnotation(Attribute.class);
            String attr = annotation.modelAttribute();
            attr = (attr == null) || (attr.isEmpty()) ? getDefaultModelAttribute(field) : attr;
            String property = annotation.controlProperty();
            try {
                StringConverter converter = annotation.converter().newInstance();
                bindControl((Node) field.get(this), property, attr, converter);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void bindControl(Node control, String controlProperty, String modelProperty, final StringConverter converter) {
        try {
            final StringProperty property = (StringProperty) control.getClass().getMethod(controlProperty + PROPERTY_SUFFIX).invoke(control);
            model.getProperty(modelProperty).bind(
                    Bindings.createObjectBinding(new Callable<Object>() {
                        @Override
                        public Object call() throws Exception {
                            return converter.fromString(property.getValue());
                        }
                    }, property));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
