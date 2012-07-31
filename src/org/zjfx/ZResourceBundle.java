package org.zjfx;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ZResourceBundle<T> extends ResourceBundle {

    public static final String ZMODEL = "ZModel";

    private ResourceBundle parent;
    private ZModel<T> model;

    public ZResourceBundle(ZModel<T> model) {
        this(null, model);
    }

    public ZResourceBundle(ResourceBundle parent, ZModel<T> model) {
        this.parent = parent;
        this.model = model;
    }

    @Override
    protected Object handleGetObject(String key) {
        return ZMODEL.equals(key) ? model : parent != null ? parent.getObject(key) : null;
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent != null ? parent.getKeys() : null;
    }
}
