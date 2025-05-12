public class Node {
 public int width;
 public int length;
 public String value;
public Node left, right,up;


         public Node(int width, int length, String value){
 this.value = value;
 this.length = length;
 this.width = width;


}

 public Node(Node left, Node right,int width, int length, String value) {
  this.width = width;
  this.length = length;
  this.value = value;
  this.left = left;
  this.right = right;
 }

 public Node(String a) {
 }

 public Node(Node left, Node right, Node up, String s) {
  this.left = left;
  this.right = right;
  this.up=up;
  this.value=s;
 }
}
