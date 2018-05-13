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

}
