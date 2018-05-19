package app.event;


import app.entity.DroneGroupDeaggregate;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;

import static app.Context.TIME_STEP;


public class MoveFormationEvent extends BasicSimStateChange<DroneGroupDeaggregate, Object> {

    public MoveFormationEvent(DroneGroupDeaggregate groupDeaggregate, double delay) throws SimControlException{
        super(groupDeaggregate, delay);
    }

    @Override
    protected void transition() throws SimControlException {
        getSimEntity().getAgents().forEach(agent -> agent.setPosition(agent.getPosition().add(new Point2D(1.0, 0.0))));
        getSimEntity().getAgents().forEach(agent ->{
            try{
                agent.sendMessages();
            }
            catch (SimControlException e) {
                e.printStackTrace();
            }
    });
        activateRepetition(TIME_STEP);
    }
}
