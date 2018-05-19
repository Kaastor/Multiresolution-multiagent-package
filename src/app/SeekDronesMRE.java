package app;

import javafx.geometry.Point2D;
import sim.formation.PositionVector;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;

class SeekDronesMRE extends DronesMRE{

    SeekDronesMRE(Context context, Point2D startingPosition, int agentsNumber, double T, double K) throws SimControlException {
        super(context, startingPosition, agentsNumber, T, K);
    }

    void initializeNetwork(){
        getNetwork().addConnections(0, new int[]{3,4});
        getNetwork().addConnections(1, new int[]{0,4});
        getNetwork().addConnections(2, new int[]{1,4});
        getNetwork().addConnections(3, new int[]{2,4});
        getNetwork().addConnections(4, new int[]{0,1,2,3});
    }

    ArrayList<PositionVector> createPositionVectors(){
        ArrayList<PositionVector> positionVectors = new ArrayList<>();
        positionVectors.add(new PositionVector(0,3, new Point2D(5,-5)));
        positionVectors.add(new PositionVector(0,4, new Point2D(0,-5)));
        positionVectors.add(new PositionVector(1,0, new Point2D(5,5)));
        positionVectors.add(new PositionVector(1,4, new Point2D(5,0)));
        positionVectors.add(new PositionVector(2,1, new Point2D(-5,5)));
        positionVectors.add(new PositionVector(2,4, new Point2D(0,5)));
        positionVectors.add(new PositionVector(3,2, new Point2D(-5,-5)));
        positionVectors.add(new PositionVector(3,4, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(4,0, new Point2D(0,5)));
        positionVectors.add(new PositionVector(4,1, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(4,2, new Point2D(0,-5)));
        positionVectors.add(new PositionVector(4,3, new Point2D(5,0)));

        return positionVectors;
    }

}
