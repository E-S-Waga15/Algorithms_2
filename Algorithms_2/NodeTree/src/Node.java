public class Node {
    int length = 0 ;
    int width = 0;
    String name;

    Node left, right,up;

    public Node( Node left, Node right, Node up,String name) {
        this.name = name;
        this.left = left;
        this.right = right;
        this.up = up;
    }

    public Node(String name ,int length,int width   ){
        this.length = length;
        this.width = width;
        this.name = name;
    }

    public Node() {

    }

}

