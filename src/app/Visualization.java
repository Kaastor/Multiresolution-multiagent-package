package app;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


class Visualization extends Pane {

    Circle circle = new Circle();

    Visualization(double sceneWidth, double sceneHeight) {
        setWidth(sceneWidth);
        setHeight(sceneHeight);
    }

    void init() throws Exception {

        circle.setStroke(Color.BLACK);
        circle.setFill(Color.BLACK);
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(10d);
        this.getChildren().addAll(circle);
    }
}
