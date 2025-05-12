import java.io.FileInputStream;
import java.util.ArrayList;

public class ImportFromFile {
 private Node root;
 public ArrayList<ArrayList<Character>> chars;
 public ImportFromFile(String path){
 chars = new ArrayList<>();
 try {
 FileInputStream fis = new FileInputStream(path);
 byte b[] = fis.readAllBytes();
 int index = 0;
 chars.add(new ArrayList<>());
 for(int i = 0; i < b.length; i++){
 if(b[i] == '\n'){
 chars.add(new ArrayList());
 index++;
 }else{
 chars.get(index).add((char) b[i]);
}
 }
 } catch (Exception ex) {
 throw new RuntimeException(ex.getMessage());
 }
 root = getNode(0, chars.size() - 1, 0, chars.get(0).size() - 1);
 }

         BinaryTree get(){
 BinaryTree bt = new BinaryTree();
 bt.root = this.root;
 return bt;
 }

    private Node getNode(int top, int down, int left, int right){

 int width = right - left;
 if(right == chars.get(0).size() - 1)
 width++;
 int length = down - top;
 char ch;
 Node leftN = null;
 Node rightN = null;
 int getHor = getHorizontal(top, down, left, right);
 int getVer = getVertical(top, down, left, right);
 if(getHor != -1){
 ch = '-';
 leftN = getNode(top, getHor + 1, left, right);
 rightN = getNode(getHor + 1, down, left, right);
 }else if(getVer != -1){
 ch = '|';
 leftN = getNode(top, down, left, getVer + 1);
 rightN = getNode(top, down, getVer + 1, right);
 }else {
 ch = getChar(top, down, left, right);
 }
Node root = new Node(width, length, "" + ch);
 root.left = leftN;
 root.right = rightN;
 return root;
 }

         public int getVertical(int top, int down, int left, int right){
 for(int i = left + 1; i < right - 1; i++){
 boolean work = true;
 for(int j = top; j < down; j++){
if(chars.get(j).get(i) == ' '){
 work = false;
 break;
 }
 }
 if(work) return i;
 }
 return -1;
 }

    public int getHorizontal(int top, int down, int left, int right){
 for(int i = top + 1; i < down - 1; i++){
 boolean work = true;
 for (int j = left; j < right; j++) {
 if(chars.get(i).get(j) == ' '){
 work = false;
 break;
 }
 }
 if(work) return i; }
return -1; }
 public char getChar(int top, int down, int left, int right){
 for (int i = top; i < down; i++) {
 for (int j = left; j < right; j++) {
 char current = chars.get(i).get(j);
if(current != ' ' && current != '-' && current != '|'){
 return current;
 }
 }
 }
 throw new RuntimeException("invalid file");
 }

}