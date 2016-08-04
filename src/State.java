/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

public class State {

    /* Attributes */
    int x;
    int y;

    /**
     * State constructor in the case of the initial state
     */
    public State(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * State constructor in the case of a successor state
     */
    public State(State successor) {
        this.x = successor.x;
        this.y = successor.y;
    }


    @Override
    public int hashCode() {
        return (Execute.height * y) + x;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (x != other.x || y != other.y)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "State [Position X = " + Integer.toString(x) + "Position Y = " + Integer.toString(y) + "]";
    }
}
