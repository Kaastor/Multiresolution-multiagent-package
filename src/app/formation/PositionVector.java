package app.formation;


import app.Agent;
import javafx.geometry.Point2D;

public class PositionVector {

    private Agent sourceAgent;
    private Point2D vector;
    private Agent targetAgent;

    public PositionVector(Agent sourceAgent, Point2D vector, Agent targetAgent) {
        this.sourceAgent = sourceAgent;
        this.vector = vector;
        this.targetAgent = targetAgent;
    }

    public Agent getSourceAgent() {
        return sourceAgent;
    }

    public void setSourceAgent(Agent sourceAgent) {
        this.sourceAgent = sourceAgent;
    }

    public Point2D getVector() {
        return vector;
    }

    public void setVector(Point2D vector) {
        this.vector = vector;
    }

    public Agent getTargetAgent() {
        return targetAgent;
    }

    public void setTargetAgent(Agent targetAgent) {
        this.targetAgent = targetAgent;
    }
}
