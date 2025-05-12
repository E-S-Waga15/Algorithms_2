import java.nio.file.Files;
import java.nio.file.Paths;

public class Helper {


        //قراءة كل الاسطر من الملف وتخزينها في مصفوفة سلاسل
        static String[] readFile(String fileName) {


            try {
                //تحويل كل الاسطر الى مصفوفة وارجاعها
                String[] lines = Files.readAllLines(Paths.get(fileName)).toArray(new String[0]);

                return lines;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;//اذا فشلت عملية القراءة ارجع null
        }
        //كتابة سلسة الى ملف
        static boolean writeFile(String str, String fileName) {


            try {
                Files.write(Paths.get(fileName), str.getBytes());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        static void charArrFill(char[][] a, char x) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    a[i][j] = x;
                }
            }
        }

        static String charArrToStr(char[][] a) {
            String s = "";
            for (char[] line : a) {
                for (char c : line) {
                    s = s + c;
                }
                s = s + "\n";
            }
            return s;
        }

        static char[][] strArrToCharArr(String[] strIn) {

            char[][] chars = new char[strIn.length][];
            for (int i = 0; i < strIn.length; i++) {
                chars[i] = strIn[i].toCharArray();
            }


            for (char[] row : chars) {
                for (char ch : row) {
                    System.out.print(ch + " ");
                }
                System.out.println();
            }
            return chars;
        }
    }







