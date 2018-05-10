package app.agent;

import app.Context;
import dissim.simspace.core.BasicSimEntity;

public class BasicAgent extends BasicSimEntity {

    int id;

    public BasicAgent(Context context){
        super(context);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
