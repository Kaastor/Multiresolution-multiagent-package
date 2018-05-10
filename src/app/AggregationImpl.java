package app;


import java.util.ArrayList;

public class AggregationImpl implements IAggregate {

    AggregationImpl(){

    }

    @Override
    public Object aggregate(Object params) {
        ResolutionLevel parent = (ResolutionLevel)params;

        System.out.println("Aggregation " + parent.isHibernated());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        return list;
    }
}
