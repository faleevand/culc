
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Main {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String ArToRoman(int number) {
        int n = map.floorKey(number);
        if (number == n) {
            return map.get(number);
        }
        return map.get(n) + ArToRoman(number - n);
    }

    static void expect(String srt) {
        System.out.println(srt);
        System.exit(1);
    }

    static int arculc(String str, int a, int b) {
        int c;
        if (str.contains("+")) {
            c = a + b;
        } else if (str.contains("-")) {
            c = a - b;
        } else if (str.contains("*")) {
            c = a * b;
        } else {
            c = a / b;
        }
        return (c);
    }

    public static String calc(String exp) {
        exp = exp.replaceAll("\\s", "");
        String[] s = exp.split("[+-/* ]");

        Pattern p = Pattern.compile("([1-9]|10)(\\s?)(\\+?|-?|\\*?|/?)(\\s?)([1-9]|10)");
        Matcher vm = p.matcher(exp);
        boolean isvm = vm.matches();

        if (isvm) {
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int c = arculc(exp, a, b);
            return (exp + " = " + c);
        }
        Pattern pattern = Pattern.compile("(I?X|IV|V?I{0,3})(\\s?)(\\+?|-?|\\*?|/?)(\\s?)(I?X|IV|V?I{0,3})");
        Matcher m = pattern.matcher(exp);
        boolean ism = m.matches();

        if (ism) {
            int a = 0;
            int b = 0;
            switch (s[0]) {
                case "X":
                    a = 10;
                    break;
                case "IX":
                    a = 9;
                    break;
                case "VIII":
                    a = 8;
                    break;
                case "VII":
                    a = 7;
                    break;
                case "VI":
                    a = 6;
                    break;
                case "V":
                    a = 5;
                    break;
                case "IV":
                    a = 4;
                    break;
                case "III":
                    a = 3;
                    break;
                case "II":
                    a = 2;
                    break;
                case "I":
                    a = 1;
                    break;
            }
            switch (s[1]) {
                case "X":
                    b = 10;
                    break;
                case "IX":
                    b = 9;
                    break;
                case "VIII":
                    b = 8;
                    break;
                case "VII":
                    b = 7;
                    break;
                case "VI":
                    b = 6;
                    break;
                case "V":
                    b = 5;
                    break;
                case "IV":
                    b = 4;
                    break;
                case "III":
                    b = 3;
                    break;
                case "II":
                    b = 2;
                    break;
                case "I":
                    b = 1;
                    break;
            }
            int trm = arculc(exp, a, b);
            if (trm > 0) {
                return (exp + " = " + ArToRoman((trm)));
            } else {
                expect("Отрицательное или нулевое значение для римских чисел.");
            }
        } else expect("Неверное выражение.");
        return (null);

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp;
        exp = input.nextLine();
        System.out.println(calc(exp));
    }
}
