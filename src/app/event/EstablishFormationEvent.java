package app.event;


import app.entity.Agent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;
import sim.event.AggregationEvent;
import sim.resolution.ResolutionLevel;


import static app.Context.TIME_STEP;
import static sim.formation.FormationControl.DISTANCE_PRECISION;


public class EstablishFormationEvent extends BasicSimStateChange<Agent, ResolutionLevel> {

    private final static Logger logger = Logger.getLogger(EstablishFormationEvent.class);

    private Point2D lastPosition;
    private Point2D nextPosition;

    public EstablishFormationEvent(Agent agent, double delay, ResolutionLevel resolution) throws SimControlException{
        super(agent, delay, resolution);
    }


    @Override
    protected void transition() throws SimControlException {
        lastPosition = getSimEntity().getPosition();
        nextPosition = getSimEntity().nextFormationPosition();
        logger.warn(simTime() + ":" + getSimEntity().getId() + " - " + getSimEntity().getPosition());
        if(!checkPositionPrecision())
            new EstablishFormationEvent(getSimEntity(), TIME_STEP, getTransitionParams());
        else
            new AggregationEvent(getTransitionParams().getChild());
    }

    private boolean checkPositionPrecision(){
        double distanceFromPredecessor = lastPosition.distance(getSimEntity().getPredecessors().get(0).getPosition());
        double desiredDistance = euclideanDistance(getSimEntity().getFormation().getConnectionVector(getSimEntity().getPredecessors().get(0), getSimEntity()));
        return Math.abs(desiredDistance - distanceFromPredecessor) < DISTANCE_PRECISION;
    }

    private double euclideanDistance(Point2D p1){
        return Math.sqrt(Math.pow(p1.getX(), 2) + Math.pow(p1.getY(), 2));
    }
}
