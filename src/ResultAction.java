/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

/**
 * This class returns the following State given a specific action
 */
public class ResultAction implements ResultFunction{

    @Override
    public Object result(Object s, Action a) {
        State state = (State) s;
        Actionn action = (Actionn) a;

        /* Here the successor state is created */
        State successor = new State(state);

        switch(action.type){
            case UP:
                successor.x = state.x;
                successor.y = state.y-1;
                break;
            case DOWN:
                successor.x = state.x;
                successor.y = state.y+1;
                break;
            case LEFT:
                successor.x = state.x-1;
                successor.y = state.y;
                break;
            case RIGHT:
                successor.x = state.x+1;
                successor.y = state.y;
                break;
            case UP_LEFT:
                successor.x = state.x-1;
                successor.y = state.y-1;
                break;
            case UP_RIGHT:
                successor.x = state.x+1;
                successor.y = state.y-1;
                break;
            case DOWN_LEFT:
                successor.x = state.x-1;
                successor.y = state.y+1;
                break;
            case DOWN_RIGHT:
                successor.x = state.x+1;
                successor.y = state.y+1;
                break;
        }

        return successor;
    }
}
