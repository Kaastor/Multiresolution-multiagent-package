package app.entity;


import app.Context;
import app.event.Message;
import app.sim.agent.BasicAgent;
import app.sim.formation.FormationControl;
import app.sim.network.Network;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import app.visualisation.Visualization;

import java.util.HashSet;
import java.util.Set;


public class Agent extends BasicAgent{

    private Circle graphicRepresentation;
    private Network network;
    private Set<Agent> predecessors;
    private FormationControl formation;

    public Agent(Context context, Network network, Point2D position){
        super(context);
        this.network = network;
        this.predecessors = new HashSet<>();
        setPosition(position);
        context.getContextEventBroker().subscribe(Message.class, this);
        initGraphicRepresentation();
    }

    public Agent(Context context, Point2D position){
        super(context);
        setPosition(position);
        initGraphicRepresentation();
    }

    private void initGraphicRepresentation(){
        graphicRepresentation = new Circle();
        graphicRepresentation.setStroke(Visualization.randomColor());
        graphicRepresentation.setFill(Visualization.randomColor());
        graphicRepresentation.setRadius(8d);
        graphicRepresentation.setCenterX(getPosition().getX());
        graphicRepresentation.setCenterY(getPosition().getY());
    }

    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        Agent sender = (Agent)iEventPublisher;
        predecessors.add(sender);
    }

    @Override
    public void reflect(IEvent iEvent) {
    }

    @Override
    public void initialize() {
        try {
            sendMessages();
        }
        catch (SimControlException e){ e.printStackTrace(); }
    }

    public Point2D nextFormationPosition() throws SimControlException{
        Point2D nextPosition = formation.nextPosition(this);
        setPosition(nextPosition);
        sendMessages();
        return nextPosition;
    }

    public Network getNetwork() {
        return this.network;
    }

    void sendMessages() throws SimControlException{
        new Message(this);
    }

    public void setFormation(FormationControl formation) {
        this.formation = formation;
    }

    public Set<Agent> getPredecessors() {
        return predecessors;
    }

    public Circle getGraphicRepresentation() {
        return graphicRepresentation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Agent{");
        sb.append("id=").append(getId());
        sb.append(", position=").append(getPosition());
        sb.append('}');
        return sb.toString();
    }
}
