import java.util.ArrayList;
import java.util.List;

public class Tree {
    Node root;
    public static void printTree(Node leaf) {
        if (leaf == null) {
            return;
        }
        System.out.println("Name: " + leaf.name + ", Length: " + leaf.length + ", Width: " + leaf.width);
        printTree(leaf.left);
        printTree(leaf.right);
    }
    //import2
    //O(n)
    public Node ImportfromFormula(String code) {
        int i = braceSkipper(code);
        root = analyser(code, i);
        printTree(root);

        return root;
    }
    private Node analyser(String code, int i){
        Node node = new Node(String.valueOf(code.charAt(i)),0, 0 );

        if(i != 0) {
            if (code.charAt(i - 1) == ']') {
                int j = i, z = i;
                while (code.charAt(j) != '[')
                    j--;
                while (code.charAt(z) != ',')//
                    z--;
                node.left = new Node(String.valueOf(code.charAt(j - 1)),Integer.parseInt(code.substring(j + 1, z)), Integer.parseInt(code.substring(z + 1, i - 1)));

            } else if (code.charAt(i - 1) == ')') {
                node.left = analyser(code, lineSkipper(code, i - 1, "Left"));
            }
        }

        if(!(i > code.length())){
            if (code.charAt(i + 1) != '(') {
                int j = i, z = i;
                while (code.charAt(j) != ']')
                    j++;
                while (code.charAt(z) != ',')
                    z++;
                if(!((z + 1) > code.length())){
                    node.right = new Node(String.valueOf(code.charAt(i + 1)),Integer.parseInt(code.substring(i + 3, z)), Integer.parseInt(code.substring(z + 1, j)));
                }

            } else if (code.charAt(i + 1) == '(') {
                node.right = analyser(code, lineSkipper(code, i + 1, "Right"));
            }
        }
        return node;
    }
    private int lineSkipper(String code, int i, String side) {
        int counter = 0, j = 0;
        if (side.equals("Left")) {
            j = i - 1;

            for (; j >= 0; j--) {
                if (code.charAt(j) == ')')
                    counter++;
                else if ((code.charAt(j) == '|' || code.charAt(j) == '-') && counter != 0)
                    counter--;
                else if ((code.charAt(j) == '|' || code.charAt(j) == '-') && counter == 0)
                    break;
            }
        } else if (side.equals("Right")) {
            j = i + 1;
            for (; j < code.length(); j++) {
                if (code.charAt(j) == '(')
                    counter++;
                else if ((code.charAt(j) == '|' || code.charAt(j) == '-') && counter != 0)
                    counter--;
                else if ((code.charAt(j) == '|' || code.charAt(j) == '-') && counter == 0)
                    break;
            }
        }
        return j;
    }
    private int braceSkipper(String code) {
        int counter = 0;
        int j = 0;
        for ( ; j < code.length() ; j++) {
            if (code.charAt(j) == '(')
                counter++;
            else if (code.charAt(j) == ')' && counter != 0)
                counter--;
            else if ((code.charAt(j) == '|' || code.charAt(j) == '-')&&(counter == 0))
                return j;
        }
        System.out.println(j);
        return 0;
    }
   // export2
    //O(n)
   public String exportToFormula(Node root) {
       if (root == null) {
           return "";
       }
       StringBuilder formula = new StringBuilder();

       if ((root.name.equalsIgnoreCase("|")) || (root.name.equalsIgnoreCase("-"))){
           formula.append("(").append(root.name);
       }
       if ((!root.name.equalsIgnoreCase("|")) && (!root.name.equalsIgnoreCase("-"))) {

           formula.append("(").append(root.name).append("[").append(root.length).append(",").append(root.width).append("]");
       }

       else
       {formula.append(exportToFormula(root.left));
           formula.append(exportToFormula(root.right));
       }
       formula.append(")");
       return formula.toString();
   }
    public static String Export(Node node){

        String code = "";
        if(node == null){
            return "";
        }

        if(node.left != null){
            if((!node.left.name.equalsIgnoreCase("|")) && (!node.left.name.equalsIgnoreCase("-"))){
                code = code + node.left.name + "[" + node.left.length + "," + node.left.width + "]";
            }
            else {
                Node left = node.left;
                code = code + "(" + Export(left) + ")";
            }
        }
        code = code + node.name;
        if(node.right != null){
            if((!node.right.name.equalsIgnoreCase("|")) && (!node.right.name.equalsIgnoreCase("-"))){
                code = code + node.right.name + "[" + node.right.length + "," + node.right.width + "]";
            }
            else {
                Node right = node.right;
                code = code + "(" + Export(right) + ")";
            }
        }
        return code;
    }
    }
