package app;


import java.util.ArrayList;

public class AggregationImpl implements IAggregate {

    AggregationImpl(){

    }

    @Override
    public Object aggregate() {
        System.out.println("Aggregation");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        return list;
    }
}
