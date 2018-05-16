package app;


import dissim.random.SimGenerator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class Visualization extends Pane {

    private static ArrayList<Agent> agents = new ArrayList<>();
    private static ArrayList<Circle> agentRepresentations = new ArrayList<>();

    private static SimGenerator generator = new SimGenerator();
    private static boolean draw = false;

    public Visualization(double sceneWidth, double sceneHeight) {
        setWidth(sceneWidth);
        setHeight(sceneHeight);
    }

    public void updatePositions(){
        if(agentRepresentations.size() > 0 && !draw) {
            getChildren().clear();
            getChildren().addAll(agentRepresentations);
            draw = true;
        }
        else if(draw){
            for (int i = 0; i < agents.size(); i++) {
                agentRepresentations.get(i).setCenterX(agents.get(i).getPosition().getX() * 15 + 300);
                agentRepresentations.get(i).setCenterY(agents.get(i).getPosition().getY() * 15 + 300);
            }
        }
    }

    static Color randomColor() {
        int range = 220;
        return Color.rgb((int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range));
    }

    static void setAgents(ArrayList<Agent> agentsList) {
        draw = false;
        agents = agentsList;
        agentRepresentations.clear();
        agentsList.forEach(agent -> agentRepresentations.add(agent.getGraphicRepresentation()));
    }

}
