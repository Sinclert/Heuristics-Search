/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.agent.impl.DynamicAction;


public class Actionn extends DynamicAction{

    public enum actionType {UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT}

    public String name;
    public int newX;
    public int newY;
    public actionType type;

    /**
     * It creates a new action receiving as arguments the type of movement and the new position
     */
    public Actionn(actionType type, int newX, int newY) {
        super(type.toString() + "_" + Integer.toString(newX) + Integer.toString(newY));
        this.name = type.toString() + " " + Integer.toString(newX) + "-" + Integer.toString(newY);
        this.type = type;
        this.newX = newX;
        this.newY = newY;
    }

    public String toString(){
        return this.name;
    }
}
