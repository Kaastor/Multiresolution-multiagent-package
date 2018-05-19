package app.event;


import app.entity.Agent;
import app.entity.DroneGroupAggregate;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;


import static app.Context.TIME_STEP;


public class WanderingEvent extends BasicSimStateChange<DroneGroupAggregate, Object> {

    private final SimGenerator simGenerator = new SimGenerator();

    private Agent agent;
    private double wanderTheta = 0;
    private double maxForce = 0.1;
    private double maxSpeed = 2.0;
    private double wanderRadius = 25.0;
    private double wanderDistance = 10.0;
    private double change = 2;
    private Point2D velocity = new Point2D(0.0,0.0);
    private Point2D acceleration = new Point2D(0.0,0.0);

    public WanderingEvent(DroneGroupAggregate aggregate) throws SimControlException{
        super(aggregate);
        this.agent = aggregate.getAgent();
        activateRepetition(TIME_STEP);
    }

    @Override
    protected void transition() throws SimControlException {
        update();
        wander();
    }

    private void wander(){
        wanderTheta += simGenerator.uniform(-change,change);
        Point2D circlePosition = velocity;
        circlePosition.normalize();
        circlePosition.multiply(wanderDistance);
        circlePosition.add(agent.getPosition());
        double h = getAngle(velocity);
        Point2D circleOffSet = new Point2D(wanderRadius *Math.cos(wanderTheta+h),
                wanderRadius *Math.sin(wanderTheta+h));
        Point2D target = circlePosition.add(circleOffSet);
        seek(target);
    }

    void seek(Point2D target){
        Point2D desired = target.subtract(agent.getPosition());
        desired.normalize();
        desired.multiply(maxSpeed);
        Point2D steer = desired.subtract(velocity);
        steer = limit(steer, maxForce);

        applyForce(steer);
    }

    public double getAngle(Point2D target) {
        return Math.atan2(target.getY(), target.getX());
    }

    private void update(){
        velocity = velocity.add(acceleration);
        velocity = limit(velocity, maxSpeed);
        agent.setPosition(agent.getPosition().add(velocity));
        acceleration.multiply(0);
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
