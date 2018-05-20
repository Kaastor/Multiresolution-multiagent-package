package app.event;


import app.entity.Agent;
import app.entity.DroneGroupAggregate;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;


import static app.Context.TIME_STEP;


public class WanderingEvent extends BasicSimStateChange<DroneGroupAggregate, Object> {

    private final Logger log = Logger.getLogger(WanderingEvent.class);

    private final SimGenerator simGenerator = new SimGenerator();

    private Agent agent;
    private double wanderTheta = 0;

    private double maxForce = 5;
    private double maxSpeed = 25;

    private double wanderRadius = 1.5;
    private double wanderDistance = 10;
    private double change = 0.7;
    private Point2D velocity = new Point2D(0.0,0.0);
    private Point2D acceleration = new Point2D(0.0,0.0);

    public WanderingEvent(DroneGroupAggregate aggregate) throws SimControlException{
        super(aggregate);
        this.agent = aggregate.getAgent();
        agent.setPosition(new Point2D(500,500));
        activateRepetition(TIME_STEP);
    }

    @Override
    protected void transition() throws SimControlException {
        wander();
        update();
    }

    private void wander(){
        wanderTheta += simGenerator.uniform(-change,change);
        Point2D circlePosition = velocity;
        circlePosition = circlePosition.normalize();
        circlePosition = circlePosition.multiply(wanderDistance);
        circlePosition = circlePosition.add(agent.getPosition());
        double h = getAngle(velocity);
        Point2D circleOffSet = new Point2D(wanderRadius *Math.cos(wanderTheta+h),
                wanderRadius *Math.sin(wanderTheta+h));
        Point2D target = circlePosition.add(circleOffSet);

        seek(target);
    }

    private void seek(Point2D target){
        Point2D desired = target.subtract(agent.getPosition());
        desired.normalize();
        desired.multiply(maxSpeed);

        Point2D steer = desired.subtract(velocity);
        steer = limit(steer, maxForce);

        applyForce(steer);
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

    static double map(double value, double start1, double stop1, double start2, double stop2) {
        return (value - start1) / (stop1 - start1) * (stop2 - start2) + start2;
    }

    private double getAngle(Point2D target) {
        return Math.atan2(target.getY(), target.getX());
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
