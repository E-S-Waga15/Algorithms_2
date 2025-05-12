/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Node {

    String value;
    List<Node> children;
  

    public Node(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

}
