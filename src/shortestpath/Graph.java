package shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author rachelmills
 */
public class Graph {

    Map<Integer, CategoryNode> graph;
    Queue<List<CategoryNode>> q;
    Map<Integer, Boolean> visited;
    List<List<CategoryNode>> paths;

    public Graph() {
        graph = new HashMap<>();
    }

    void addEdge(int src, int dest) {
        CategoryNode srcNode = getOrCreateNode(src);
        CategoryNode destNode = getOrCreateNode(dest);
        srcNode.addAdjacent(destNode);
    }

    private CategoryNode getOrCreateNode(int id) {
        CategoryNode node = graph.get(id);
        if (null == node) {
            node = new CategoryNode(id);
            graph.put(id, node);
        }
        return node;
    }

    public List<CategoryNode> findShortestPath(int src, int dest) {
        visited = new HashMap<>();
        q = new LinkedList<>();
        CategoryNode nodeSrc = graph.get(src);
        CategoryNode nodeDest = graph.get(dest);
        List<CategoryNode> cn = new LinkedList<>();
        cn.add(nodeSrc);
        List<CategoryNode> sp = new ArrayList<>();
        if (nodeSrc != null && nodeDest != null) {
            q.add(cn);

            sp = findPath(q, nodeDest);
        }
        return sp;
    }

    private List<CategoryNode> findPath(Queue<List<CategoryNode>> q, CategoryNode nodeDest) {
        List<CategoryNode> path = null;

        while (!q.isEmpty() && path == null) {
            path = checkPath(q.poll(), nodeDest);
        }

        if (path != null && !path.isEmpty()) {
            System.out.println("Path");
            for (CategoryNode node : path) {
                System.out.print("id = " + node.getId() + "\n");
            }
        }

        return path;
    }

    private List<CategoryNode> checkPath(List<CategoryNode> nodes, CategoryNode dest) {
        CategoryNode lastNode = nodes.get(nodes.size() - 1);

        if (!lastNode.equals(dest)) {
            if (!visited.containsKey(lastNode.getId())) {
                visited.put(lastNode.getId(), Boolean.TRUE);

                for (CategoryNode node : lastNode.getAdjacencies()) {

                    List<CategoryNode> newList = new ArrayList<>();
                    newList.addAll(nodes);
                    newList.add(node);
                    q.add(newList);
                }
            }
        } else {
            return nodes;
        }
        return null;
    }

    public List<CategoryNode> addAdjacenciesForShortestPath(int srcNode, List<CategoryNode> shortestPath) {
        List<Integer> newAdjacencies = null;
        if (null != graph.get(srcNode)) {

            int countAdj = graph.get(srcNode).getAdjacencies().size();
            newAdjacencies = new ArrayList<>();
            
            for (CategoryNode node : shortestPath) {
                
                if (!graph.get(srcNode).getAdjacencies().contains(node) && node.getId() != srcNode) {
                    graph.get(srcNode).addAdjacent(node);
                    newAdjacencies.add(node.getId());
                }
            }
            int countNewAdj = graph.get(srcNode).getAdjacencies().size();
            if (countNewAdj != countAdj) {
                System.out.println("Source node = " + srcNode);
                System.out.println("All adjacencies:  ");
                for (CategoryNode adj : graph.get(srcNode).getAdjacencies()) {
                    System.out.print(adj.getId() + ", ");
                }
                System.out.print("\n");
                System.out.println("New adjacencies:  ");
                for (Integer i : newAdjacencies) {
                    System.out.print(i + ", ");
                }
                System.out.print("\n");
            }
            return graph.get(srcNode).getAdjacencies();
        }
        return null;

    }
}
