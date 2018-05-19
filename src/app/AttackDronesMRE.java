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

    }

    ArrayList<PositionVector> createPositionVectors(){
        ArrayList<PositionVector> positionVectors = new ArrayList<>();


        return positionVectors;
    }
}
