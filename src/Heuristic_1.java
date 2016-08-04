/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.search.framework.HeuristicFunction;

/**
 * Implements the Manhattan distance heuristic
 */
public class Heuristic_1 implements HeuristicFunction{

    @Override
    public double h(Object state) {
        State currentState = (State)state;

        int distanceX = Math.abs(currentState.x - Execute.finalX);
        int distanceY = Math.abs(currentState.y - Execute.finalY);

        return (distanceX + distanceY);
    }
}
