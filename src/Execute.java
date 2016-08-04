/**
 * Authors:
 *
 * Sinclert Perez (100317201)
 * Daniel Brinzei (100318049)
 */

import static java.lang.System.exit;
import java.io.*;
import java.util.List;
import aima.core.agent.Action;
import aima.core.search.framework.*;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;


/**
 * Main class of the project, the one who executes the search algorithms
 */
public class Execute {

    public static char [][] initialMap;
    public static int height = 0;
    public static int width = 0;
    public static int initialX;
    public static int initialY;
    public static int finalX;
    public static int finalY;


    private static Algorithm getAlgorithm(String arg) {

        if (arg.equalsIgnoreCase("Breadth")) {
            return Algorithm.Breadth;
        }
        else if (arg.equalsIgnoreCase("Depth")) {
            return Algorithm.Depth;
        }
        else if (arg.equalsIgnoreCase("Astar")) {
            return Algorithm.Astar;
        }
        else if (arg.equalsIgnoreCase("GBFS")) {
            return Algorithm.GBFS;
        }
        else {
            System.err.println("Unknown algorithm: " + arg);
            exit(-1);
            return null;
        }
    }

    private static Heuristic getHeuristic(String arg) {

        if (arg.equalsIgnoreCase("Manhattan")) {
            return Heuristic.Manhattan;
        }
        else if (arg.equalsIgnoreCase("Euclidean")) {
            return Heuristic.Euclidean;
        }
        else if (arg.equalsIgnoreCase("Chebyshev")) {
            return Heuristic.Chebyshev;
        }
        else {
            System.err.println("Unknown heuristic: " + arg);
            exit(-1);
            return null;
        }
    }

    // Possible algorithms to execute the search are identified here
    public enum Algorithm {
        Breadth, Depth, Astar, GBFS
    }

    // Possible heuristics to execute the search are identified here
    public enum Heuristic {
        Manhattan, Euclidean, Chebyshev
    }


    public static void main(String args[]) throws IOException {

        if (args.length != 5) {
            System.out.println("Syntax: java Execute <Map> <Algorithm> <Heuristic> <Initial pos> <Final pos>");
            exit(0);
        }

        Algorithm algorithm = getAlgorithm(args[1]);
        Heuristic heuristic = getHeuristic(args[2]);

        // The file in that location is opened
        FileReader f = new FileReader(args[0]);
        BufferedReader b = new BufferedReader(f);

        // The file is read
        String line;
        while (height == 0 || width == 0) {

            line = b.readLine();
            if (line.contains("height")) {
                height = Integer.parseInt(line.substring(7));

            }
            else if (line.contains("width")) {
                width = Integer.parseInt(line.substring(6));
            }
        }

        initialMap = new char [height][width];
        b.readLine();

        // Our matrix is filled with the values of the file matrix
        for (int row = 0 ; row < height ; row++) {

            // Every value of each row is read and stored
            line = b.readLine();
            for (int column = 0 ; column < width ; column++) {
                initialMap[row][column] = line.charAt(column);
            }
        }

        // Closing Buffer reader
        b.close();

        // Storing initial and final positions
        String [] initialPos = args[3].split("-");
        String [] finalPos = args[4].split("-");

        initialX = Integer.parseInt(initialPos[0]);
        initialY = Integer.parseInt(initialPos[1]);
        finalX = Integer.parseInt(finalPos[0]);
        finalY = Integer.parseInt(finalPos[1]);

        // Checks if the initial position is a wall or not
        if (initialMap[initialY][initialX] == '@'){
            System.out.println("There is not possible solution: the initial position is a wall");
            exit(0);
        }

        // Checks if the final position is a wall or not
        if (initialMap[finalY][finalX] == '@'){
            System.out.println("There is not possible solution: the final position is a wall");
            exit(0);
        }

        State initialState = new State(Integer.parseInt(initialPos[0]), Integer.parseInt(initialPos[1]));


        // Initialization of the functions which are required to compute the search
        AvailableActions avActions = new AvailableActions();
        ResultAction actionResults = new ResultAction();
        GoalFunction goalFunction = new GoalFunction();
        ActionCost actionCost = new ActionCost();

        try {
            Problem problem = new Problem(initialState, avActions,
                    actionResults, goalFunction, actionCost);

            HeuristicFunction hf = null;
            if (heuristic != null){
                switch (heuristic) {
                    case Manhattan:
                        hf = new Heuristic_1();
                        break;
                    case Euclidean:
                        hf = new Heuristic_2();
                        break;
                    case Chebyshev:
                        hf = new Heuristic_3();
                        break;
                    default:
                        System.out.println("Invalid Heuristic");
                        exit(-1);
                }
            }

            Search search = null;
            if (algorithm != null) {
                switch (algorithm) {
                    case Breadth:
                        search = new BreadthFirstSearch();
                        break;
                    case Depth:
                        search = new DepthFirstSearch(new GraphSearch());
                        break;
                    case Astar:
                        search = new AStarSearch(new GraphSearch(), hf);
                        break;
                    case GBFS:
                        search = new GreedyBestFirstSearch(new GraphSearch(), hf);
                        break;
                    default:
                        System.out.println("Invalid Algorithm");
                        exit(-1);
                }
            }

            long t1 = System.currentTimeMillis();
            List<Action> actionList = search.search(problem);
            long t2 = System.currentTimeMillis();

            // Checks if the final position can be reached from the initial one
            if (actionList.size() == 0){
                System.out.println("There is no possible path between the initial and final points");
                exit(-1);
            }

            double time = (t2 - t1) / 1000.0;

            generateMap(args[0], actionList, initialMap);
            generateStatistics(args[0], actionList, search.getMetrics(), time);
            System.out.println("The search has been completed and the corresponding files generated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function generates the final map with the route within the same folder where the initial map was
     */
    private static void generateMap(String path, List<Action> actions, char [][] map) throws FileNotFoundException {
        map[initialY][initialX] = 'I';
        int posY = initialY;
        int posX = initialX;

        for (int i = 0; i < actions.size(); i++) {
            String action = actions.get(i).toString();
            if (action.contains("UP_LEFT")){
                posY = posY - 1;
                posX = posX - 1;
            }
            else if (action.contains("UP_RIGHT")) {
                posY = posY - 1;
                posX = posX + 1;
            }
            else if (action.contains("DOWN_LEFT")) {
                posY = posY + 1;
                posX = posX - 1;
            }
            else if (action.contains("DOWN_RIGHT")) {
                posY = posY + 1;
                posX = posX + 1;
            }
            else if (action.contains("UP")) {
                posY = posY - 1;
            }
            else if (action.contains("DOWN")) {
                posY = posY + 1;
            }
            else if (action.contains("LEFT")) {
                posX = posX - 1;
            }
            else if (action.contains("RIGHT")) {
                posX = posX + 1;
            }
            map[posY][posX] = 'X';
        }

        map[finalY][finalX] = 'F';

        // Here the final map is generated and stored
        File f = new File(path + ".out");
        PrintWriter writer = new PrintWriter(f);

        // Finally, the final map is wrote in the generated file
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                writer.print(map[i][j]);
            }
            writer.println();
        }
        writer.close();
    }

    /**
     * This function generates the statistics about the search within the same folder where the initial map was
     */
    private static void generateStatistics(String path, List <Action> actions, Metrics metrics, double time) throws FileNotFoundException {

        // Here the final statistics are generated
        File f = new File(path + ".statistics");
        PrintWriter writer = new PrintWriter(f);

        writer.println("Actions:");
        for (int i = 0 ; i < actions.size() ; i++){
            writer.println(actions.get(i));
        }

        writer.println();
        writer.println("Time: " + time + " s");
        writer.println("Plan Length: " + actions.size());

        for (String key : metrics.keySet()) {
            String property = metrics.get(key);
            writer.println(key + " : " + property);
        }

        writer.close();
    }
}
