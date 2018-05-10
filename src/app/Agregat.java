package app;


import app.resolution.MultiresolutionEntity;
import dissim.simspace.core.SimControlException;
import app.events.AggregationEvent;
import app.resolution.IAggregate;
import app.resolution.ResolutionLevel;

import java.util.ArrayList;

public class Agregat extends ResolutionLevel {

    public Agregat(Context context, MultiresolutionEntity entity, IAggregate impl) throws SimControlException {
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
