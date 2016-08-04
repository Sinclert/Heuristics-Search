/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.search.framework.GoalTest;

/**
 * This class decide is we have reached the final point or not, returning true or false
 */
public class GoalFunction implements GoalTest {

    public boolean isGoalState(Object state) {
        State currentState = (State)state;

        if (currentState.x == Execute.finalX &&  currentState.y == Execute.finalY){
            return true;
        }
        else {
            return false;
        }
    }
}
