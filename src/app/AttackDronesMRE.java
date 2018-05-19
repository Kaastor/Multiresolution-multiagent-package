package app;


import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import sim.formation.PositionVector;

import java.util.ArrayList;

class AttackDronesMRE extends DronesMRE{


    AttackDronesMRE(Context context, Point2D startingPosition, int agentsNumber, double T, double K) throws SimControlException {
        super(context, startingPosition, agentsNumber, T, K);
    }

    void initializeNetwork(){
        getNetwork().addConnections(0, new int[]{1, 2});
        getNetwork().addConnections(1, new int[]{0,2,3});
        getNetwork().addConnections(2, new int[]{0,1,4});
        getNetwork().addConnections(3, new int[]{1});
        getNetwork().addConnections(4, new int[]{2});
    }

    ArrayList<PositionVector> createPositionVectors(){
        ArrayList<PositionVector> positionVectors = new ArrayList<>();
        positionVectors.add(new PositionVector(0,1, new Point2D(-3,-3)));
        positionVectors.add(new PositionVector(0,2, new Point2D(3,-3)));
        positionVectors.add(new PositionVector(1,0, new Point2D(3,3)));
        positionVectors.add(new PositionVector(1,3, new Point2D(-3,-3)));
        positionVectors.add(new PositionVector(2,0, new Point2D(-3,3)));
        positionVectors.add(new PositionVector(2,4, new Point2D(3,-3)));
        positionVectors.add(new PositionVector(3,1, new Point2D(3,3)));
        positionVectors.add(new PositionVector(4,2, new Point2D(-3,3)));

        positionVectors.add(new PositionVector(1,2, new Point2D(6,0)));
        positionVectors.add(new PositionVector(2,1, new Point2D(-6,0)));

        return positionVectors;
    }
}
