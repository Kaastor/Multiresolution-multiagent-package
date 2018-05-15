package app.sim.network;

import javafx.geometry.Point2D;
import org.jgrapht.graph.DefaultWeightedEdge;



public class Link extends DefaultWeightedEdge {

    private Point2D vector;

    public Point2D getVector() {
        return vector;
    }

    public void setVector(Point2D vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Link{");
        sb.append(super.toString());
        sb.append(",vector=").append(vector);
        sb.append("\n}");
        return sb.toString();
    }
}
