import java.util.Scanner;
import java.util.TreeMap;
public class Main {

        public static class Converter{
            public static void main(String[] args){

                Scanner scn = new Scanner(System.in);
                System.out.println("Введите выражение: ");
                String exp = scn.nextLine();
                Converter converter = new Converter();
                String[] actions = {"+", "-", "/", "*"};
                String[] regexActions = {"\\+", "-", "/", "\\*"};


            //Определяем арифметическое действие:
            int actionIndex = -1;
            for (int i = 0; i < actions.length; i++) {
                if (exp.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }

            }
            //Если не нашли арифметического действия, завершаем программу
            if (actionIndex == -1) {
                System.out.println("Некорректное выражение");
                return;
            }
            if (actionIndex < 2){
                System.out.println("Не более 2 операндов");
                return;
            }
            //"2-4".split("-")-> {"2", "4"}
            String[] data = exp.split(regexActions[actionIndex]);

            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b;
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                } else

                {
                    //конвертируем арабские числа из строки в число
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }
                // число не должно быть больше 10
                 if (a > 10 || b > 10) {
                    System.out.println("Число  должно быть от 1 до 10  ");
                    return;
                    }

                //выполняем с числами арифметическое действие
                int result;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                } else {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }
            } else {
                System.out.println("Числа должны быть в одном формате");
            }
        }

        TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
        TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

        public Converter() {

            romanKeyMap.put('I', 1);
            romanKeyMap.put('V', 5);
            romanKeyMap.put('X', 10);
            romanKeyMap.put('L', 50);
            romanKeyMap.put('C', 100);
            romanKeyMap.put('D', 500);
            romanKeyMap.put('M', 1000);

            arabianKeyMap.put(1000, "M");
            arabianKeyMap.put(900, "CM");
            arabianKeyMap.put(500, "D");
            arabianKeyMap.put(400, "CD");
            arabianKeyMap.put(100, "C");
            arabianKeyMap.put(90, "XC");
            arabianKeyMap.put(50, "L");
            arabianKeyMap.put(40, "XL");
            arabianKeyMap.put(10, "X");
            arabianKeyMap.put(9, "IX");
            arabianKeyMap.put(8, "VIII");
            arabianKeyMap.put(7, "VII");
            arabianKeyMap.put(6, "VI");
            arabianKeyMap.put(5, "V");
            arabianKeyMap.put(4, "IV");
            arabianKeyMap.put(1, "I");
        }

        public boolean isRoman(String number) {
            return romanKeyMap.containsKey(number.charAt(0));
        }

        public String intToRoman(int number) {
            String roman = "";
            int arabianKey;
            do {
                arabianKey = arabianKeyMap.floorKey(number);
                roman += arabianKeyMap.get(arabianKey);
                number -= arabianKey;
            } while (number != 0);
            return roman;
        }

        public int romanToInt(String s) {
            int end = s.length() - 1;
            char[] arr = s.toCharArray();
            int arabian;
            int result = romanKeyMap.get(arr[end]);
            for (int i = end - 1; i >= 0; i--) {
                arabian = romanKeyMap.get(arr[i]);
                if (arabian < romanKeyMap.get(arr[i + 1])) {
                    result -= arabian;
                } else {
                    result += arabian;
                }
            }
            return result;
        }
    }}
//  import java.math.BigDecimal;
//import java.math.BigInteger;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(1-0.9);
//        System.out.println(0.2+0.1);
//        // повышенная точность
//        BigDecimal a = BigDecimal.valueOf(1);
//        BigDecimal b = BigDecimal.valueOf(0.9);
//        BigDecimal result = a.subtract(b);
//        System.out.println(result);
//        // работать с большими числами
//        long l =999999999999999999l;
//        BigInteger big1 = BigInteger.valueOf(l);
//        BigInteger res = big1.multiply(BigInteger.valueOf(l));
//        System.out.println(res);
//    }
//
//}

