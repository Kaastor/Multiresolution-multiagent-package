package app.event;


import app.entity.Agent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;
import sim.resolution.ResolutionLevel;

import java.util.ArrayList;

import static app.Context.TIME_STEP;
import static sim.formation.FormationControl.AGGREGATION_DISTANCE_PRECISION;

public class AggregateFormationEvent extends BasicSimStateChange<Agent, ResolutionLevel> {

    private final static Logger logger = Logger.getLogger(AggregateFormationEvent.class);

    private Point2D lastPosition;
    private Point2D nextPosition;

    public AggregateFormationEvent(Agent agent, double delay, ResolutionLevel resolution) throws SimControlException {
        super(agent, delay, resolution);
    }

    @Override
    protected void transition() throws SimControlException {
        lastPosition = getSimEntity().getPosition();
        nextPosition = nextAggregationPosition(getSimEntity());
        logger.warn(simTime() + ":" + getSimEntity().getId() + " - " + getSimEntity().getPosition());
        if(!checkPositionPrecision())
            new AggregateFormationEvent(getSimEntity(), TIME_STEP, getTransitionParams());

    }

    private Point2D nextAggregationPosition(Agent agent) throws SimControlException{
        Point2D nextPosition = mean(agent.getPredecessors());
        agent.setPosition(nextPosition);
        agent.sendMessages();
        return nextPosition;
    }

    private Point2D mean(ArrayList<Agent> predecessors){
        Point2D meanPoint = new Point2D(0.0,0.0);
        for(Agent predecessor : predecessors){
            meanPoint = meanPoint.add(predecessor.getPosition());
        }
        return meanPoint.multiply(1.0/predecessors.size());
    }

    private boolean checkPositionPrecision(){
        return euclideanDistance(lastPosition, nextPosition) < AGGREGATION_DISTANCE_PRECISION;
    }

    private double euclideanDistance(Point2D p1, Point2D p2){
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }
}
