import java.io.FileOutputStream;

public class ExpotingToFile {
 private char chars[][];
 private final BinaryTree bt;
 private final String filePath;
 private final int xWidth = 1;
 public ExpotingToFile(BinaryTree bt, String path) {
 this.bt = bt;
 this.filePath = path;
 }
 public void export(){
 init();
 fill(bt.root, 0, 0);
 print();
 }

        private void init(){
 chars = new char[bt.getLength()][bt.getWidht() * xWidth];
 for(int i = 0; i < bt.getLength(); i++){
 for (int j = 0; j < bt.getWidht() * xWidth; j++) {
 if(i == 0){
 chars[i][j] = '-';
 }else if(j == 0){
 chars[i][j] = '|';
}else{
 chars[i][j] = ' '; }
 }
 }
}
         private void fill(Node n, int skipTop, int skipLeft){
 if(n == null) return;
 fill(n.left, skipTop, skipLeft);
 int addTop = 0;
 int addLeft = 0;
 if("-".equals(n.value)){
 addTop = n.left.length;
 }else if("|".equals(n.value)){
 addLeft = n.left.width * xWidth;
 }else{
 for(int i = 0; i < n.length; i++){
 chars[i + skipTop][skipLeft + n.width * xWidth - 1] = '|';
 }
 for(int i = 0; i < n.width * xWidth; i++){
 chars[skipTop + n.length - 1][i + skipLeft] = '-';
 }
 chars[skipTop + n.length / 2][skipLeft + n.width * xWidth / 2] = n.value.charAt(0);
 return;
 }
 fill(n.right, skipTop + addTop, skipLeft + addLeft);
 }

         private void print(){
 try {
 FileOutputStream fos = new FileOutputStream(filePath);
 for(int i = 0; i < bt.getLength(); i++){
 for (int j = 0; j < bt.getWidht() * xWidth; j++) {
 fos.write((int) chars[i][j]);
 }
 fos.write('\n');
 }
 } catch (Exception ex) {
 }
 }

}
