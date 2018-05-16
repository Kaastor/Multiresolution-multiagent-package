package app;


import app.sim.formation.FormationControl;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;


import static app.ObiektSymulacyjny.TIME_STEP;
import static app.sim.formation.FormationControl.POSITION_PRECISION;


public class MoveEvent extends BasicSimStateChange<Agent, Object> {

    private Point2D lastPosition;
    private Point2D nextPosition;

    public MoveEvent(Agent agent, double delay) throws SimControlException{
        super(agent, delay);
    }

    public MoveEvent(Agent agent) throws SimControlException{
        super(agent);
    }

    @Override
    protected void transition() throws SimControlException {
        lastPosition = getSimEntity().getPosition();
        nextPosition = getSimEntity().nextFormationPosition();
        System.out.println(simTime() + ":" + getSimEntity().getId() + " - " + getSimEntity().getPosition());
        if(!checkPositionPrecision())
            new MoveEvent(getSimEntity(), TIME_STEP);
    }

    private boolean checkPositionPrecision(){
        return euclideanDistance(lastPosition, nextPosition) < POSITION_PRECISION;
    }

    private double euclideanDistance(Point2D p1, Point2D p2){
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }
}
