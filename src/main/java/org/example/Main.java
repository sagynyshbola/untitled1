package org.example;

import algorithms.Kruskal;
import algorithms.Prim;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.InputFile;
import data.MSTResult;
import data.OutputModel;
import data.GraphModel;
import graph.Graph;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/resources/assign_3_input.json");
        InputFile input = gson.fromJson(reader, InputFile.class);
        reader.close();

        OutputModel output = new OutputModel();

        for (GraphModel model : input.graphs) {
            Graph graph = new Graph(model.nodes, model.edges);

            System.out.println("Processing Graph ID: " + model.id);

            Prim prim = new Prim(graph);
            MSTResult primResult = prim.findMST();

            Kruskal kruskal = new Kruskal(graph);
            MSTResult kruskalResult = kruskal.findMST();

            output.addResult(model.id, graph, primResult, kruskalResult);
        }

        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("src/main/resources/output.json");
        gsonPretty.toJson(output, writer);
        writer.close();

        System.out.println("✅ MST results saved to output.json");
    }
}
