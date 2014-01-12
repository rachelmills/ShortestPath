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
    private final List<CategoryNode> shortest;
    private FileOutputStream fos;
    static private Writer out;
    private List<Integer> srcCategories = new ArrayList<>();
    List<String> mainTopics = new ArrayList<>();
    List<Integer> mainTopicIDs = new ArrayList<>();

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //     ShortestPath sp = new ShortestPath("/Users/rachelmills/Desktop/ClueWeb/ShortestPath/ID_Title_Categories.txt");
        ShortestPath sp = new ShortestPath("/Users/rachelmills/Desktop/ClueWeb/WikiParser/Test9.txt");
     //   ShortestPath sp = new ShortestPath("/home/wikiprep/wikiprep/work/Wikiparser/ID_Title_Categories.txt");

        // add all main topics to list so they can be extracted separately
        sp.setMainTopics();

        sp.processLineByLine();

        List<CategoryNode> path;

        for (Integer cat : sp.getSrcCategories()) {
            sp.getShortest().clear();
            for (Integer i : sp.mainTopicIDs) {

                path = sp.findShortestPath(cat, i);

                if ((path != null && !path.isEmpty()) && ((null == sp.getShortest() || sp.getShortest().isEmpty()) || (path.size() < sp.getShortest().size()))) {
                    sp.getShortest().clear();
                    sp.getShortest().addAll(path);
                }
            }

            List<CategoryNode> list = sp.addAdjacenciesForShortestPath(cat, sp.getShortest());
            sp.writeAllCategoriesToFile(list, cat);
        }

        log("Done.");
    }

    private void setMainTopics() {
        mainTopics.add("Agriculture");
        mainTopics.add("Arts");
        mainTopics.add("Belief");
        mainTopics.add("Business");
        mainTopics.add("Chronology");
        mainTopics.add("Concepts");
        mainTopics.add("Culture");
        mainTopics.add("Education");
        mainTopics.add("Environment");
        mainTopics.add("Geography");
        mainTopics.add("Health");
        mainTopics.add("History");
        mainTopics.add("Humanities");
        mainTopics.add("Humans");
        mainTopics.add("Language");
        mainTopics.add("Law");
        mainTopics.add("Life");
        mainTopics.add("Mathematics");
        mainTopics.add("Medicine");
        mainTopics.add("Nature");
        mainTopics.add("People");
        mainTopics.add("Politics");
        mainTopics.add("Science");
        mainTopics.add("Society");
        mainTopics.add("Sports");
        mainTopics.add("Technology");
    }

    public ShortestPath(String filename) {
        g = new Graph();
        fFilePath = Paths.get(filename);
        shortest = new ArrayList<>();
        try {
            fos = new FileOutputStream("ID_Categories_Updated_Test.txt");
        } catch (FileNotFoundException e) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.INFO, "Exception is {0}", e);
        }
        try {
            out = new OutputStreamWriter(fos, "UTF8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<CategoryNode> addAdjacenciesForShortestPath(int srcNode, List<CategoryNode> shortestPath) {
        return g.addAdjacenciesForShortestPath(srcNode, shortestPath);
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
        String topic = sc.next();

        if (mainTopics.contains(topic)) {
            mainTopicIDs.add(src);
            System.out.println("Topic is:  " + topic);
            System.out.println("Topic id is:  " + src);
        } else {
            getSrcCategories().add(src);
        }

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

    private void writeAllCategoriesToFile(List<CategoryNode> list, int srcNode) {

        try {
            out.write(String.valueOf(srcNode) + " ");
            if (null != list) {
                for (CategoryNode node : list) {
                    out.write(String.valueOf(node.getId()) + " ");
                }
            }

            out.write("\n");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the shortest
     */
    public List<CategoryNode> getShortest() {
        return shortest;
    }

    /**
     * @return the srcCategories
     */
    public List<Integer> getSrcCategories() {
        return srcCategories;
    }
}
