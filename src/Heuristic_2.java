/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.search.framework.HeuristicFunction;

/**
 * Implements the Euclidean distance heuristic
 */
public class Heuristic_2 implements HeuristicFunction{

    @Override
    public double h(Object state) {
        State currentState = (State)state;

        int distanceX = Math.abs(currentState.x - Execute.finalX);
        int distanceY = Math.abs(currentState.y - Execute.finalY);

        int cat1 = distanceX * distanceX;
        int cat2 = distanceY * distanceY;

        double result = cat1 + cat2;
        result = Math.sqrt(result);

        return result;
    }
}
