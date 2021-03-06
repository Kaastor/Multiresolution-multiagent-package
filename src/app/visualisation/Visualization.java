package app.visualisation;


import app.entity.Agent;
import dissim.random.SimGenerator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class Visualization extends Pane {

    public static final double AGGREGATE_CIRCLE_RADIUS = 15d;
    public static final double DEAGGREGATE_CIRCLE_RADIUS = 8d;

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
                agentRepresentations.get(i).setCenterX(agents.get(i).getPosition().getX());
                agentRepresentations.get(i).setCenterY(agents.get(i).getPosition().getY());
            }
        }
    }

    public static Color randomColor() {
        int range = 220;
        return Color.rgb((int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range), (int) (generator.nextDouble() * range));
    }

    public static void addResolutionAgentsToDraw(ArrayList<Agent> agentsList) {
        draw = false;
        agents.addAll(agentsList);
        updateAgentRepresentations();
    }

    public static void removeResolutionAgentsToDraw(ArrayList<Agent> agentsList){
        draw = false;
        agents.removeAll(agentsList);
        updateAgentRepresentations();
    }

    private static void updateAgentRepresentations(){
        agentRepresentations.clear();
        agents.forEach(agent -> agentRepresentations.add(agent.getGraphicRepresentation()));
    }

}
