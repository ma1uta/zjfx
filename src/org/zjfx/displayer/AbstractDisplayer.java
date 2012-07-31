package org.zjfx.displayer;

import org.zjfx.ZModel;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class AbstractDisplayer extends Parent {

    protected ZModel<?> model;
    protected String attr;

    public ZModel<?> getModel() {
        return model;
    }

    public void setModel(ZModel<?> model) {
        this.model = model;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getAttr() {
        return attr;
    }

    public void init() {
        getChildren().add(paint());
    }

    public abstract Node paint();
}
