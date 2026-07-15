import java.util.*;

public class MetroRouteFinder {

    private Map<String, List<String>> graph;

    public MetroRouteFinder() {
        graph = new HashMap<>();
    }

    public void addStation(String station) {
        graph.putIfAbsent(station, new ArrayList<>());
    }

    public void addConnection(String station1, String station2) {
        graph.get(station1).add(station2);
        graph.get(station2).add(station1);
    }

    public List<String> findRoute(String source, String destination) {

        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        Map<String, String> parent = new HashMap<>();

        queue.offer(source);

        visited.add(source);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(destination))
                break;

            for (String neighbor : graph.get(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    parent.put(neighbor, current);

                    queue.offer(neighbor);

                }

            }

        }

        List<String> path = new ArrayList<>();

        if (!visited.contains(destination))
            return path;

        String current = destination;

        while (current != null) {

            path.add(current);

            current = parent.get(current);

        }

        Collections.reverse(path);

        return path;

    }

    public static void main(String[] args) {

        MetroRouteFinder metro = new MetroRouteFinder();


        metro.addStation("Aluva");
        metro.addStation("Pulinchodu");
        metro.addStation("Companypady");
        metro.addStation("Pathadipalam");
        metro.addStation("Edappally");
        metro.addStation("Changampuzha Park");
        metro.addStation("Palarivattom");


        metro.addConnection("Aluva", "Pulinchodu");
        metro.addConnection("Pulinchodu", "Companypady");
        metro.addConnection("Pulinchodu", "Pathadipalam");
        metro.addConnection("Companypady", "Pathadipalam");
        metro.addConnection("Pathadipalam", "Edappally");
        metro.addConnection("Edappally", "Changampuzha Park");
        metro.addConnection("Changampuzha Park", "Palarivattom");


        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================");
            System.out.println("     METRO ROUTE FINDER");
            System.out.println("==============================");
            System.out.println("1. Display All Stations");
            System.out.println("2. Find Shortest Route");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\nAvailable Stations:");
                    int i = 1;

                    for (String station : metro.graph.keySet()) {
                        System.out.println(i + ". " + station);
                        i++;
                    }

                    break;

                case 2:

                    System.out.print("Enter Source Station: ");
                    String source = sc.nextLine().trim();

                    System.out.print("Enter Destination Station: ");
                    String destination = sc.nextLine().trim();

                    if (!metro.graph.containsKey(source) || !metro.graph.containsKey(destination)) {
                        System.out.println("Invalid Station Name!");
                        break;
                    }

                    List<String> route = metro.findRoute(source, destination);

                    if (route.isEmpty()) {
                        System.out.println("No Route Found!");
                    } else {
                        System.out.println("\nShortest Route:");

                        for (int j = 0; j < route.size(); j++) {
                            System.out.print(route.get(j));

                            if (j != route.size() - 1)
                                System.out.print(" -> ");
                        }

                        System.out.println("\nTotal Stations: " + route.size());
                        System.out.println("Stops: " + (route.size() - 1));
                    }

                    break;

                case 3:

                    System.out.println("Thank you for using Metro Route Finder!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice!");

            }

        }

    }
}