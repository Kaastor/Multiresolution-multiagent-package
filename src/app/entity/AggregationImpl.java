package app.entity;


import app.sim.resolution.IAggregation;
import app.sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class AggregationImpl implements IAggregation {

    public AggregationImpl(){
    }

    @Override
    public Object aggregate(Object param) {
        ResolutionLevel parent = (ResolutionLevel) param;

        System.out.println("Aggregation " + parent.isHibernated());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        return list;
    }
}
