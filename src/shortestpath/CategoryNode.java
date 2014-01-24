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
    
    private int id;
    private List adjacencies;
    
    public CategoryNode() {}

    public CategoryNode(int id) {
        adjacencies = new ArrayList<>();
        this.id = id;
    }
    
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
