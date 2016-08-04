/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class add the possible actions given a state to a list of possible actions, which is returned at the end
 */
public class AvailableActions implements ActionsFunction{

    public Set<Action> actions(Object state) {
        State State = (State)state;
        Set<Action> Actions = new LinkedHashSet<>();

        /* UP */
        if (State.y != 0 && Execute.initialMap[State.y-1][State.x] != '@'){
            Actions.add(new Actionn(Actionn.actionType.UP, State.x, State.y-1));
        }

        /* DOWN */
        if (State.y != Execute.height-1 && Execute.initialMap[State.y+1][State.x] != '@'){
            Actions.add(new Actionn(Actionn.actionType.DOWN, State.x, State.y+1));
        }

        /* LEFT */
        if (State.x != 0 && Execute.initialMap[State.y][State.x-1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.LEFT, State.x-1, State.y));
        }

        /* RIGHT */
        if (State.x != Execute.width-1 && Execute.initialMap[State.y][State.x+1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.RIGHT, State.x+1, State.y));
        }

        /* UP_LEFT */
        if (State.y != 0 && State.x != 0 && Execute.initialMap[State.y-1][State.x-1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.UP_LEFT, State.x-1, State.y-1));
        }

        /* UP_RIGHT */
        if (State.y != 0 && State.x != Execute.width-1 && Execute.initialMap[State.y-1][State.x+1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.UP_RIGHT, State.x+1, State.y-1));
        }

        /* DOWN_LEFT */
        if (State.y != Execute.height-1 && State.x != 0 && Execute.initialMap[State.y+1][State.x-1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.DOWN_LEFT, State.x-1, State.y+1));
        }

        /* DOWN_RIGHT */
        if (State.y != Execute.height-1 && State.x != Execute.width-1 && Execute.initialMap[State.y+1][State.x+1] != '@'){
            Actions.add(new Actionn(Actionn.actionType.DOWN_RIGHT, State.x+1, State.y+1));
        }

        return Actions;
    }
}
