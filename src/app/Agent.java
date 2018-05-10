package app;

import dissim.simspace.core.BasicSimEntity;

public class Agent extends BasicSimEntity {

    int id;

    public Agent(Context context){
        super(context);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
