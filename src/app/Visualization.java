package app;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;


class Visualization extends Pane {

    private ArrayList<Agent> agents = ObiektSymulacyjny.getAgents();
    private ArrayList<Circle> agentCircles = new ArrayList<>();

    private Random random = new Random();

    Visualization(double sceneWidth, double sceneHeight) {
        setWidth(sceneWidth);
        setHeight(sceneHeight);
    }

    void init(int agentNumber) throws Exception {
        for(int i = 0 ; i < agentNumber ; i++){
            Circle circle = new Circle();
            circle.setStroke(randomColor());
            circle.setFill(randomColor());
            circle.setRadius(8d);
            agentCircles.add(circle);
            this.getChildren().add(circle);
        }
    }

    void draw(){
        for(int i = 0 ; i < agents.size() ; i++){
            agentCircles.get(i).setCenterX(agents.get(i).getPosition().getX()*15+300);
            agentCircles.get(i).setCenterY(agents.get(i).getPosition().getY()*15+300);
        }
    }

    public Color randomColor() {
        int range = 220;
        return Color.rgb((int) (random.nextDouble() * range), (int) (random.nextDouble() * range), (int) (random.nextDouble() * range));
    }
}
