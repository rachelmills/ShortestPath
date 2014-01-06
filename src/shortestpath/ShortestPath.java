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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rachelmills
 */
public class ShortestPath {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Graph g = new Graph();

        ShortestPath parser1 = new ShortestPath("/Users/rachelmills/Desktop/ClueWeb/WikiParser/Test7.txt");
        Scanner scanner = new Scanner(parser1.fFilePath, ENCODING.name());
        while (scanner.hasNextLine()) {
            Scanner scanner2 = new Scanner(scanner.nextLine());
            scanner2.useDelimiter(" ");
            int src = scanner2.nextInt();
            int dest = scanner2.nextInt();
            g.addEdge(src, dest);
            
        }
        g.findShortestPath(1, 8);
        
        log("Done.");
        
  
    }

    public ShortestPath(String filename) {
        fFilePath = Paths.get(filename);
    }
    
 /*   private void processLineByLine() throws IOException {
        try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        }
    }
  */  
 /*   private void processLine(String nextLine) {
         
             String next;
        //use a second Scanner to parse the content of each line 
        Scanner scanner = new Scanner(nextLine);
        scanner.useDelimiter("#");
        
        if (scanner.hasNextLine()) {
         List<Integer> myList2 = new ArrayList<>();
         LinkedList<Integer> myList3 = new LinkedList<>();
             
            //assumes the line has a certain structure
            Integer id = scanner.nextInt();
            myList2.add(id);
            myList3.add(id);
            String reject = scanner.next();
            if (scanner.hasNext()) {
                next = scanner.nextLine().substring(1);
                List<String> myList = Arrays.asList(next.split(" "));
                
                categories.put(id, myList);
                
                for (String s : myList) {
                    myList2.add(Integer.parseInt(s));
                    myList3.add(Integer.parseInt(s));
                }
                
                
            } else {
                next = "no categories";
                categories.put(id, null);
                
            }
            
            cats.add(myList2);
            cats2.add(myList3);
        } else {
            log("Empty or invalid line. Unable to process.");
        }
    }
  */  
    HashMap<Integer,List<String>> categories = new HashMap<>();
    List<List<Integer>> cats = new ArrayList<>();
    List<LinkedList<Integer>> cats2 = new ArrayList<>();
    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    
 

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
