package app.communication;


import app.agent.BasicAgent;

import java.util.ArrayList;

public interface Messageable {
    ArrayList<BasicAgent> getNeighbours();
}
