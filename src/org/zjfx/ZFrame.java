package org.zjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;

public class ZFrame extends Application {

    private ZWindow window;

    @Override
    public void start(final Stage stage) {

        window = new ZWindow(stage, "") {

            @Override
            protected Node createForm() {
                return RectangleBuilder.create().width(300).height(200).fill(Color.WHITESMOKE).build();
            }
        };

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.getStage().show();
                window.getStage().toFront();
            }
        });
    }
}
