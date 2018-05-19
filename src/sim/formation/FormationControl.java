package sim.formation;


import app.entity.Agent;
import javafx.geometry.Point2D;

import java.util.ArrayList;



public class FormationControl {

    public static final double DISTANCE_PRECISION = 0.05;
    public static final double AGGREGATION_DISTANCE_PRECISION = 0.01;

    private FormationGraph formationGraph;
    private double T;
    private double K;

    public FormationControl(FormationGraph formationGraph, double T, double K) {
        this.formationGraph = formationGraph;
        this.T = T;
        this.K = K;

    }

    public Point2D getConnectionVector(Agent from, Agent to){
        return formationGraph.getConnection(from, to).getVector();
    }

    public Point2D nextPosition(Agent agent){
        return agent.getPosition().add( velocityVector(agent).multiply(T) );
    }

    private Point2D velocityVector(Agent agent){
        return agent.getPosition().subtract( relativeDesiredPosition(agent) ).multiply(-(K/T));
    }

    private Point2D relativeDesiredPosition(Agent agent){
        Point2D desiredPosition = new Point2D(0.0,0.0);
        ArrayList<Agent> predecessors = agent.getPredecessors();
        int predecessorsNumber = predecessors.size();

        for(Agent predecessor : predecessors){
            desiredPosition = desiredPosition.add(predecessor.getPosition());
            desiredPosition = desiredPosition.add(formationGraph.getConnection(predecessor, agent).getVector());
        }

        return  desiredPosition.multiply(1.0/predecessorsNumber);
    }

    public Point2D formationError(Agent agent){
        return agent.getPosition().subtract(relativeDesiredPosition(agent));

    }

}
