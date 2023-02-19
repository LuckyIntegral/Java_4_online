package ua.com.alevel.controller;

import ua.com.alevel.service.Graph;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWorker {
    /*
    Джарник, який збирає мавен - знаходить файл module_2/input.txt, і тіки його !
    Ідейка у свою чергу не знаходить файл input.txt, якщо він не записаний module_2/input.txt
    Я старався :)
     */
    private static final Path input = Paths.get("input.txt");
    private static final Path output = Paths.get("output.txt");
    public void start() {
        try (BufferedReader reader = new BufferedReader(new FileReader(input.toFile()))) {
            int size = Integer.parseInt(reader.readLine());
            Graph graph = new Graph(size);
            for (int i = 0; i < size; i++) {
                String name = reader.readLine();
                graph.addVertex(i, name); // Indexes within system start from 0
                int neigh = Integer.parseInt(reader.readLine());
                for (int j = 0; j < neigh; j++) { // Indexation within file from 1 !!!
                    String[] idCosts = reader.readLine().split(" ");
                    graph.addWay(i, Integer.parseInt(idCosts[0]) - 1, Integer.parseInt(idCosts[1]));
                }
            }
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String[] terms = reader.readLine().split(" ");
                int cost = Graph.findBestWays(graph.cityIndex(terms[0]), graph.getWays())[graph.cityIndex(terms[1])];
                writeResult(terms[0], terms[1], cost);
            }
        } catch (Exception e) {
            System.out.println("Something wrong with input file, check it and try again");
        }
        System.out.println("Check results in output.txt");
    }

    private void writeResult(String from, String to, int cost) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.toFile(), true))) {
            writer.write("The best way from " + from + " to " + to + " costs " + cost + '$' + '\n');
        } catch (Exception e) {
            System.out.println("Something went wring during writing results, try again");
        }
    }
}
