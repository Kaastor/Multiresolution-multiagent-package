package app.sim.formation;


import app.Agent;
import app.TestEvent;
import app.sim.agent.BasicAgent;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;

import java.util.Set;


public class FormationControl {

    private FormationGraph formationGraph;
    private double T;
    private double K;

    public FormationControl(FormationGraph formationGraph, double T, double K) {
        this.formationGraph = formationGraph;
        this.T = T;
        this.K = K;
        initializeFormation();
    }

    private void initializeFormation(){
        formationGraph.getAgents().forEach(BasicAgent::initialize);
//        formationGraph.getAgents().forEach(agent -> {
//            try {
//                new TestEvent((Agent)agent);
//            }
//            catch (SimControlException e) {e.printStackTrace();}});
    }

    public Point2D nextPosition(Agent agent){
        return agent.getPosition().add( velocityVector(agent).multiply(T) );
    }

    private Point2D velocityVector(Agent agent){
        return agent.getPosition().subtract( relativeDesiredPosition(agent) ).multiply(-(K/T));
    }

    private Point2D relativeDesiredPosition(Agent agent){
        Point2D desiredPosition = new Point2D(0.0,0.0);
        Set<Agent> predecessors = agent.getPredecessors();
        int predecessorsNumber = predecessors.size();

        for(Agent predecessor : predecessors){
            desiredPosition = desiredPosition.add(predecessor.getPosition());
            desiredPosition = desiredPosition.add(formationGraph.getConnection(predecessor, agent).getVector());
        }

        return  desiredPosition.multiply(1.0/predecessorsNumber);
    }

}
