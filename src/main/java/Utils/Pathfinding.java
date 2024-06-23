package Utils;

import Entities.Cities.City;
import Entities.Cities.CityList;
import World.WorldGenerator;

import java.util.*;

public class Pathfinding {
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static boolean[][] visited;

    public static boolean isConnectedCity(ArrayList<ArrayList<Integer>> map, int startx, int starty, boolean[][] visited) {
        try {
            if (CityList.getList().isEmpty()) return true;
            int[] start = {startx, starty};
            int[] goal = CityList.getList().getFirst().getCoordinates();
            int x = start[0];
            int y = start[1];

            // Sprawdzenie, czy osiągnięto cel
            if ((x == goal[0] - 1 && y == goal[1]) ||
                    (x == goal[0] + 1 && y == goal[1]) ||
                    (x == goal[0] && y == goal[1] + 1) ||
                    (x == goal[0] && y == goal[1] - 1)
            ) {
                return true;
            }

            // Oznaczenie aktualnej pozycji jako odwiedzonej
            visited[x][y] = true;

            // Iteracja przez wszystkie możliwe kierunki
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // Sprawdzenie, czy nowa pozycja jest w granicach mapy i jest wodą, oraz czy nie była odwiedzona
                if (nx >= 0 && nx < map.size() && ny >= 0 && ny < map.getFirst().size() && map.get(ny).get(nx) == 0 && !visited[nx][ny]) {
                    if (isConnectedCity(map, nx, ny, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }catch(StackOverflowError e){
            System.out.println("dupa");
        }
        return false;
        }

    public class AStarAlgorithm {
        // Kierunki ruchu: prawo, dół, lewo, góra
        static int[][] directions = {
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        // Klasa Node jako klasa wewnętrzna statyczna
        static class Node implements Comparable<Node> {
            int x, y;
            int g, h, f;
            Node parent;

            Node(int x, int y) {
                this.x = x;
                this.y = y;
                this.g = 0;
                this.h = 0;
                this.f = 0;
                this.parent = null;
            }

            @Override
            public int compareTo(Node other) {
                return Integer.compare(this.f, other.f);
            }
        }

        public static List<int[]> aStar(ArrayList<ArrayList<Integer>> grid, int[] start, int[] goal) {
            int rows = grid.size();
            int cols = grid.get(0).size();
            boolean[][] closed = new boolean[rows][cols];
            PriorityQueue<Node> open = new PriorityQueue<>();
            Node startNode = new Node(start[0], start[1]);
            Node goalNode = new Node(goal[0], goal[1]);
            startNode.h = heuristic(startNode, goalNode);
            startNode.f = startNode.h;
            open.add(startNode);

            while (!open.isEmpty()) {
                Node current = open.poll();

                closed[current.x][current.y] = true;

                // Przetwarzanie sąsiadów
                for (int[] dir : directions) {
                    int nx = current.x + dir[0];
                    int ny = current.y + dir[1];
                    if (nx == goalNode.x && ny == goalNode.y) {
                        return reconstructPath(current);
                    }
                    // Sprawdzanie granic i przeszkód
                    if (nx < 0 || nx >= rows || ny < 0 || ny >= cols || grid.get(ny).get(nx) == 1 || closed[nx][ny]) {
                        continue;
                    }

                    Node neighbor = new Node(nx, ny);
                    neighbor.g = current.g + 1;
                    neighbor.h = heuristic(neighbor, goalNode);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = current;

                    boolean skip = false;

                    // Sprawdzanie, czy sąsiad jest już w kolejce z niższym kosztem
                    for (Node node : open) {
                        if (node.x == neighbor.x && node.y == neighbor.y && node.f <= neighbor.f) {
                            skip = true;
                            break;
                        }
                    }

                    // Dodanie sąsiada do kolejki priorytetowej
                    if (!skip) {
                        open.add(neighbor);
                    }
                }
            }

            // Brak ścieżki
            return Collections.emptyList();
        }

        private static int heuristic(Node a, Node b) {
            // Heurystyka Manhattan dla siatki
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }

        private static List<int[]> reconstructPath(Node node) {
            List<int[]> path = new ArrayList<>();
            while (node != null) {
                path.add(new int[]{node.x, node.y});
                node = node.parent;
            }
            Collections.reverse(path);
            return path;
        }

        public boolean largestWater() {
            return true;
        }
    }

    public static boolean nearCity(int[] coordinates) {
        for (City c : CityList.getList()) {
            if (Math.pow(c.getCoordinates()[0] - coordinates[0], 2) + Math.pow(c.getCoordinates()[1] - coordinates[1], 2) >= 25)
                return true;
        }
        return false;
    }
}
