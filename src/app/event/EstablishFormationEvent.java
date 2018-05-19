package app.event;


import app.entity.Agent;
import app.entity.DroneGroupAggregate;
import app.entity.DroneGroupDeaggregate;
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
        if(!checkPositionPrecision())
            new EstablishFormationEvent(getSimEntity(), TIME_STEP, getTransitionParams());
        else{
            DroneGroupDeaggregate droneGroupDeaggregate = (DroneGroupDeaggregate)getTransitionParams().getChild();
            if(droneGroupDeaggregate.getSynchronization()){
                DroneGroupAggregate droneGroupAggregate = (DroneGroupAggregate)getTransitionParams();

                droneGroupAggregate.deagreggationTimeMonitor.setValue(droneGroupDeaggregate.simTime() -
                        droneGroupAggregate.deaggregationStart);

                droneGroupDeaggregate.deactivate();
                new AggregationEvent(getTransitionParams().getChild());
            }
        }
    }

    private boolean checkPositionPrecision(){
        return lastPosition.distance(nextPosition) < DISTANCE_PRECISION;
    }

}
