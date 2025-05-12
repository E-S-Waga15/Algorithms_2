public class Rectangle {
 int width;
 int length;
 String name;
 BinaryTree bt;
 public Rectangle(int width, int length, String name, BinaryTree bt) {
 this.width = width;
 this.length = length;
 this.name = name;
 this.bt = bt;
 }
 public Rectangle(int width, int length, String name) {
 this.width = width;
 this.length = length;
 this.name = name;
 this.bt = new BinaryTree();
 this.bt.root = new Node(width, length, name);
 }

}
