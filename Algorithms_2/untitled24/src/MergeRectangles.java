import java.util.ArrayList;
//الطلب الرابع لصق مستطيلات بجانب بعضها باستخدام شجرة ثنائية
public class MergeRectangles {
//متغير يحتفظ بقائمة من المستطيلات التي لايمكن دمجها اكثر
 public ArrayList<Rectangle> answers;
 public MergeRectangles(ArrayList<Rectangle> rectangles) {//يتم تمرير مجموعة من المستطيلات الى الكونستركتر
  //ينشئ قائمة  ويستدعي الدالة tryMerge لدمج المستطيلات
 answers = new ArrayList<>();
tryMerge(rectangles);
 }

//تدمج مجموعة من المستطيلات في ال rectanglesواذا كانت القايمة تحتوي على مستطيل واحد فقط يضاف هذا المستطيل الى ال answers وتنتهي العملية

         private void tryMerge(ArrayList<Rectangle> rectangles) {
 if (rectangles.size() == 1) {
 answers.add(rectangles.get(0));
return;
 }
//نستخدم حلقتين الفور لمحاولة دمج كل زوج ممكن من المستطيلات في القائمة
          //تتم عملية الدمج اذا كانت ابعاد المستطيل متطابقة
 for (int i = 0; i < rectangles.size(); i++) {
 for (int j = 0; j < rectangles.size(); j++) {
if(i == j) continue;
//يتم انشاء شجرة ثنائية جديدةلكل دمج ناجح ويضاف المستطيل الناتج للقائمة remaining وبعد كل عملية دمج تستدعي الدالة tryMerge بشكل تكراري وتخزن النواتج النهائية في الanswers
 ArrayList<Rectangle> remaining = new ArrayList<>(rectangles);
 Rectangle r1 = rectangles.get(i);
 Rectangle r2 = rectangles.get(j);
 remaining.remove(r1);
remaining.remove(r2);

 if(r1.length == r2.length){
 BinaryTree bt = new BinaryTree();
 bt.root = new Node(r1.width + r2.width, r1.length, "|");
 bt.root.left = r1.bt.root;
bt.root.right = r2.bt.root;
Rectangle merge = new Rectangle(r1.width + r2.width, r1.length, "|", bt);
 remaining.add(merge);
 tryMerge(remaining);
 remaining.remove(merge);
 }
 if(r1.width == r2.width){
BinaryTree bt = new BinaryTree();
 bt.root = new Node(r1.width, r1.length + r2.length, "-");
 bt.root.left = r1.bt.root;
 bt.root.right = r2.bt.root;
 Rectangle merge = new Rectangle(r1.width, r1.length + r2.length, "-", bt);
 remaining.add(merge);
 tryMerge(remaining);
 remaining.remove(merge);
 }
}
 }
 }

}
