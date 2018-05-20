package app.event;


import app.entity.Agent;
import app.entity.DroneGroupAggregate;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;
import sim.event.DeaggregationEvent;

import static app.App.SCENE_HEIGHT;
import static app.App.SCENE_OFFSET;
import static app.App.SCENE_WIDTH;
import static app.Context.TIME_STEP;

public class MissionEvent extends BasicSimStateChange<DroneGroupAggregate, Object> {

    private final Logger log = Logger.getLogger(MissionEvent.class);

    private final SimGenerator simGenerator = new SimGenerator();

    private Agent agent;
    private double maxForce = 5;
    private double maxSpeed = 25;

    private Point2D velocity = new Point2D(0.0,0.0);
    private Point2D acceleration = new Point2D(0.0,0.0);
    private Point2D target;

    MissionEvent(DroneGroupAggregate aggregate, double delay) throws SimControlException{
        super(aggregate, delay);
        this.agent = aggregate.getAgent();
        target = new Point2D(simGenerator.uniform(SCENE_OFFSET, SCENE_WIDTH-SCENE_OFFSET),
                simGenerator.uniform(SCENE_OFFSET, SCENE_HEIGHT-SCENE_OFFSET));
        activateRepetition(TIME_STEP);
    }


    @Override
    protected void transition() throws SimControlException {
        log.warn(simTime() + " - " + agent.getId() + ": Seeking target...");
        arrive(target);
        update();

        if(agent.getPosition().distance(target) < 2.0){
            deactivateRepetition();
            this.terminate();
            new DeaggregationEvent(getSimEntity(), 1.0);
        }
    }

    private void arrive(Point2D target){
        Point2D desired = target.subtract(agent.getPosition());
        double d = desired.magnitude();

        if( d < 100){
            double m = map(d, 0 , 100,0, maxSpeed);
            desired = desired.multiply(1.0/m);
        }
        else{
            desired = desired.multiply(maxSpeed);
        }
        Point2D steer = desired.subtract(velocity);
        steer = limit(steer, maxForce);
        applyForce(steer);
    }

    private double map(double value, double start1, double stop1, double start2, double stop2) {
        return (value - start1) / (stop1 - start1) * (stop2 - start2) + start2;
    }

    private void update(){
        velocity = velocity.add(acceleration);
        velocity = limit(velocity, maxSpeed);
        agent.setPosition(agent.getPosition().add(velocity));
        acceleration = acceleration.multiply(0);
    }

    private void applyForce(Point2D force){
        acceleration = acceleration.add(force);
    }

    private Point2D limit(Point2D vector, double max) {
        if (vector.magnitude() > max) {
            return (vector.multiply(1d
                    / vector.magnitude())).multiply(max);
        }
        else
            return vector;
    }

}
