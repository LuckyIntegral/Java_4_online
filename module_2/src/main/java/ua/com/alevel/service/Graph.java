package ua.com.alevel.service;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    private final Map<String, Integer> vertexes = new TreeMap<>();
    private final int[][] ways;
    public Graph(int size) {
        ways = new int[size][size];
        for (int[] way : ways) {
            Arrays.fill(way, 0);
        }
    }

    public static int[] findBestWays(int source, int[][] ways) {
        boolean[] visited = new boolean[ways.length];
        Arrays.fill(visited, false);

        int[] costs = new int[ways.length];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[source] = 0;

        for (int i = 0; i < ways.length - 1; i++) {
            int min = findMinCost(visited, costs);
            visited[min] = true;
            for (int j = 0; j < visited.length; j++) {
                if (!visited[j] && ways[min][j] != 0) {
                    int temp = costs[min] + ways[min][j];
                    if (temp < costs[j]) {
                        costs[j] = temp;
                    }
                }
            }
        }

        return costs;
    }

    private static int findMinCost(boolean[] visited, int[] costs) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && costs[i] < min) {
                min = costs[i];
                index = i;
            }
        }

        return index;
    }

    public void addVertex(int id, String name) {
        if (vertexes.containsKey(name)) {
            throw new SecurityException();
        }
        vertexes.put(name, id);
    }

    public void addWay(int from, int to, int cost) {
        if (from < 0 || to < 0 || cost < 0) {
            throw new SecurityException();
        }
        ways[from][to] = cost;
    }

    public int cityIndex(String name) {
        return vertexes.get(name); //Indexes within start from 0, not 1
    }

    public int[][] getWays() {
        return ways;
    }
}
