package org.zjfx.displayer;

import javafx.scene.Node;
import javafx.scene.text.TextBuilder;

public class StringDisplayer extends AbstractDisplayer {

    @Override
    public Node paint() {
        return TextBuilder.create().text(getModel().getString(getAttr())).build();
    }
}
