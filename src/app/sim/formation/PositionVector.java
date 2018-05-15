package app.sim.formation;


import javafx.geometry.Point2D;

public class PositionVector {

    private int sourceAgentId;
    private Point2D vector;
    private int targetAgentId;

    public PositionVector(int sourceAgentId, int targetAgentId, Point2D vector) {
        this.sourceAgentId = sourceAgentId;
        this.vector = vector;
        this.targetAgentId = targetAgentId;
    }

    public int getSourceAgentId() {
        return sourceAgentId;
    }

    public void setSourceAgentId(int sourceAgentId) {
        this.sourceAgentId = sourceAgentId;
    }

    public Point2D getVector() {
        return vector;
    }

    public void setVector(Point2D vector) {
        this.vector = vector;
    }

    public int getTargetAgentId() {
        return targetAgentId;
    }

    public void setTargetAgentId(int targetAgentId) {
        this.targetAgentId = targetAgentId;
    }
}
