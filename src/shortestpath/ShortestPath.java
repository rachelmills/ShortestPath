/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rachelmills
 */
public class ShortestPath {
    
    Graph g;
    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ShortestPath sp = new ShortestPath("/Users/rachelmills/Desktop/ClueWeb/WikiParser/Test9.txt");
        
        sp.processLineByLine();
        sp.findShortestPath(647, 1660);
        log("Done.");
    }

    public ShortestPath(String filename) {
        g = new Graph();
        fFilePath = Paths.get(filename);
    }
    
    private void findShortestPath(int src, int dest) {
        g.findShortestPath(src, dest);
    }
    
    private void processLineByLine() throws IOException {
        try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        }
    }
    
    private void processLine(String nextLine) {
         
        String next;
        //use a second Scanner to parse the content of each line 
        Scanner sc = new Scanner(nextLine);
        sc.useDelimiter("#");
        
        int src = sc.nextInt();
        
        sc.next();
        
        while (sc.hasNext()) {
            next = sc.nextLine().substring(1);
            List<String> myList = Arrays.asList(next.split(" "));
            for (String s : myList) {
                g.addEdge(src, Integer.parseInt(s));
            }
        }
    }

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
