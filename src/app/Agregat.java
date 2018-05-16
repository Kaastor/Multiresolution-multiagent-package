package app;


import app.sim.resolution.MultiresolutionEntity;
import dissim.simspace.core.SimControlException;
import app.sim.events.AggregationEvent;
import app.sim.resolution.IAggregation;
import app.sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class Agregat extends ResolutionLevel {

    public Agregat(Context context, MultiresolutionEntity entity, IAggregation impl) throws SimControlException {
        super(context, entity,null, null, impl);

        new AggregationEvent(this, 10);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void stateChange(Object result) {
        System.out.println("aggregation Transition");
        if (result != null){
            ArrayList<Integer> resultList = (ArrayList<Integer>) result;
            System.out.println(resultList.toString());
        }

    }

}
