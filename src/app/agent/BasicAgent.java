package app.agent;

import app.Context;
import dissim.simspace.core.BasicSimEntity;

public class BasicAgent extends BasicSimEntity {

    private static int idCount;
    private final int id;

    public BasicAgent(Context context){
        super(context);
        this.id = idCount++;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicAgent agent = (BasicAgent) o;

        return id == agent.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "BasicAgent{" +
                "id=" + id +
                '}';
    }
}
