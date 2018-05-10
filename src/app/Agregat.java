package app;


import dissim.simspace.core.SimControlException;
import events.AggregationEvent;
import resolution.IAggregate;
import resolution.ResolutionLevel;

import java.util.ArrayList;

public class Agregat extends ResolutionLevel {

    public Agregat(Context context, IAggregate impl) throws SimControlException {
        super(context, null, null, impl);

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
