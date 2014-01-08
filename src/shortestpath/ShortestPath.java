/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rachelmills
 */
public class ShortestPath {

    Graph g;
    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    List<CategoryNode> shortest;
    private FileOutputStream fos;
    static private Writer out;
    List<Integer> srcCategories = new ArrayList<>();

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ShortestPath sp = new ShortestPath("/Users/rachelmills/Desktop/ClueWeb/WikiParser/Test9.txt");

        // CategoryNode srcNode = new CategoryNode();
      //  int srcNode = 1642;

        List<Integer> destCategories = new ArrayList<>();
        destCategories.add(647);
        destCategories.add(646);
        destCategories.add(645);

        sp.processLineByLine();
        //      sp.findShortestPath(647, 1660);
        List<CategoryNode> path;

        for (Integer cat : sp.srcCategories) {
            for (Integer i : destCategories) {

                path = sp.findShortestPath(cat, i);

                if (path != null && ((null == sp.shortest || sp.shortest.isEmpty()) || (path.size() < sp.shortest.size()))) {
                    sp.shortest.clear();
                    sp.shortest.addAll(path);
                }
            }

            sp.addAdjacenciesForShortestPath(cat, sp.shortest);
            sp.writeAllCategoriesToFile(cat);
        }

        log("Done.");
    }

    public ShortestPath(String filename) {
        g = new Graph();
        fFilePath = Paths.get(filename);
        shortest = new ArrayList<>();
    }

    private void addAdjacenciesForShortestPath(int srcNode, List<CategoryNode> shortestPath) {
        g.addAdjacenciesForShortestPath(srcNode, shortestPath);
    }

    private List<CategoryNode> findShortestPath(int src, int dest) {
        List<CategoryNode> sp = g.findShortestPath(src, dest);
        return sp;
    }

    private void processLineByLine() throws IOException {
        srcCategories = new ArrayList<>();
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
        srcCategories.add(src);

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

    private void writeAllCategoriesToFile(int srcNode) {
        try {
            fos = new FileOutputStream("ID_Title_Categories_Updated.txt");
        } catch (FileNotFoundException e) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.INFO, "Exception is {0}", e);
        }
        try {
            out = new OutputStreamWriter(fos, "UTF8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(g.graph.get(srcNode).getId());
        try {
            out.write(String.valueOf(g.graph.get(srcNode).getId()) + " ");
            for (CategoryNode node : g.graph.get(srcNode).getAdjacencies()) {
                out.write(String.valueOf(node.getId()) + " ");
            }
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
