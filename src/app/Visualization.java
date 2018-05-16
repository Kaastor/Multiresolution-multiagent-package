package app;


import dissim.random.SimGenerator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


class Visualization extends Pane {

    private static ArrayList<Agent> agents = new ArrayList<>();
    private static ArrayList<Circle> agentRepresentations = null;

    private static SimGenerator generator = new SimGenerator();
    private boolean draw = false;

    Visualization(double sceneWidth, double sceneHeight) {
        setWidth(sceneWidth);
        setHeight(sceneHeight);
    }

    void updatePositions(){
        if(agentRepresentations != null && !draw) {
            getChildren().addAll(agentRepresentations);
            draw = true;
        }
        else {
            for (int i = 0; i < agents.size(); i++) {
                agentRepresentations.get(i).setCenterX(agents.get(i).getPosition().getX() * 15 + 100);
                agentRepresentations.get(i).setCenterY(agents.get(i).getPosition().getY() * 15 + 100);
            }
        }
    }

    static Color randomColor() {
        int range = 220;
        return Color.rgb((int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range));
    }

    static void setAgents(ArrayList<Agent> agentsList) {
        agentRepresentations = new ArrayList<>();
        agents = agentsList;
        agentsList.forEach(agent -> agentRepresentations.add(agent.getGraphicRepresentation()));
        App.animationTimer.start();

    }

}
