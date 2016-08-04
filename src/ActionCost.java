/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;

public class ActionCost implements StepCostFunction{

    @Override
    public double c(Object s, Action a, Object sDelta) {
        State state = (State) s;

        /* In order to calculate the cost, the cost in the current position is the one considered */
        double cost;
        switch (Execute.initialMap[state.y][state.x]){
            case '@':
                cost = Double.POSITIVE_INFINITY;
                break;
            case '.':
                cost = 1;
                break;
            case 'S':
                cost = 2;
                break;
            case 'W':
                cost = 2;
                break;
            case 'T':
                cost = 4;
                break;
            default:
                cost = 1;
                break;
        }

        return cost;
    }
}
