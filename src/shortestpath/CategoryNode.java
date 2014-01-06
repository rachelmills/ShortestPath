/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shortestpath;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rachelmills
 */
public class CategoryNode {
    
    private int distance;
    private CategoryNode precedingNode;
    private int id;
    private boolean visited;
    private List adjacencies;
    
    public CategoryNode() {}

    public CategoryNode(int id, boolean b) {
        adjacencies = new ArrayList<>();
        this.id = id;
    //    this.precedingNode = categoryNode;
     //   this.distance = steps;
        this.visited = b;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the parentNodes
     */
  //  public List getParentNodes() {
 //       return parentNodes;
 //   }

    /**
     * @param parentNodes the parentNodes to set
     */
 //   public void setParentNodes(List parentNodes) {
//        this.parentNodes = parentNodes;
 //   }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }   

    /**
     * @return the visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return the precedingNode
     */
    public CategoryNode getPrecedingNode() {
        return precedingNode;
    }

    /**
     * @param precedingNode the precedingNode to set
     */
    public void setPrecedingNode(CategoryNode precedingNode) {
        this.precedingNode = precedingNode;
    }

    /**
     * @return the adjacencies
     */
    public List<CategoryNode> getAdjacencies() {
        return adjacencies;
    }

    /**
     * @param adjacencies the adjacencies to set
     */
    public void setAdjacencies(List adjacencies) {
        this.adjacencies = adjacencies;
    }
    
    public void addAdjacent(CategoryNode node) {
        adjacencies.add(node);
    }
}
