package app.event;


import app.entity.Agent;
import app.entity.DroneGroupAggregate;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;


import static app.App.SCENE_HEIGHT;
import static app.App.SCENE_OFFSET;
import static app.App.SCENE_WIDTH;
import static app.Context.TIME_STEP;


public class WanderEvent extends BasicSimStateChange<DroneGroupAggregate, Object> {

    private final Logger log = Logger.getLogger(WanderEvent.class);

    private final SimGenerator simGenerator = new SimGenerator();

    private Agent agent;
    private double circleRadius;
    private double wanderTheta = 0;

    private double maxForce = 5;
    private double maxSpeed = 25;

    private double wanderRadius = 1.5;
    private double wanderDistance = 15;
    private double change = 0.65;
    private Point2D velocity = new Point2D(0.0,0.0);
    private Point2D acceleration = new Point2D(0.0,0.0);
    private double missionTime;
    private double stopTime;

    private MissionEvent mission;

    WanderEvent(DroneGroupAggregate aggregate, double delay) throws SimControlException{
        super(aggregate, delay);
        this.agent = aggregate.getAgent();
        circleRadius = agent.getGraphicRepresentation().getRadius();
        activateRepetition(TIME_STEP);
        missionTime = simGenerator.uniformInt(20, 40);
        stopTime = simTime()+missionTime;
        mission = new MissionEvent(aggregate, missionTime);
    }

    public WanderEvent(DroneGroupAggregate aggregate) throws SimControlException {
        this(aggregate, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        wander();
        update();
        borders();
        if (simTime() == stopTime) {
            deactivateRepetition();
            this.terminate();
        }
        log.warn(simTime() + " - " + agent.getId() + ": Wandering... " + missionTime);
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

    private void borders() {
        if (agent.getPosition().getX() < -circleRadius) {
            agent.setPosition(
                    new Point2D(SCENE_WIDTH + circleRadius, agent.getPosition().getY())
            );
        }
        if (agent.getPosition().getY() < -circleRadius) {
            agent.setPosition(
                    new Point2D(agent.getPosition().getX(), SCENE_HEIGHT + circleRadius)
            );
        }
        if (agent.getPosition().getX() > SCENE_WIDTH + circleRadius) {
            agent.setPosition(
                    new Point2D(-circleRadius, agent.getPosition().getY())
            );
        }
        if (agent.getPosition().getY() > SCENE_HEIGHT + circleRadius) {
            agent.setPosition(
                    new Point2D(agent.getPosition().getX(), -circleRadius)
            );
        }
    }


}
