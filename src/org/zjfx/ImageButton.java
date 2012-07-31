package org.zjfx;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Parent {

    private Image inactive;
    private Image active;
    private Image clicked;
    private ImageView imageView;
    private StringProperty image;

    public ImageButton() {
        this.getChildren().add(imageView = new ImageView());
    }

    public ImageButton(String imageResource, final EventHandler<ActionEvent> actionEventEventHandler) {
        this();
        setHandler(actionEventEventHandler);
        setImage(imageResource);
    }

    public void setHandler(final EventHandler<ActionEvent> actionEventEventHandler) {
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                actionEventEventHandler.handle(new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget()));
            }
        });
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        inactive = new Image(getClass().getResourceAsStream("images/" + image + "-inactive.png"));
        active = new Image(getClass().getResourceAsStream("images/" + image + "-active.png"));
        clicked = new Image(getClass().getResourceAsStream("images/" + image + "-clicked.png"));

        imageView.setImage(inactive);

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setImage(active);
            }
        });
        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setImage(inactive);
            }
        });
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setImage(clicked);
            }
        });
        imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setImage(active);
            }
        });
    }
}
