import java.security.MessageDigest;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        System.out.println(encrypt("Hello"));
        System.out.println(encrypt("Sunshine"));
        System.out.println(decrypt(new int[] {72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(decrypt(new int[] {83, 34, -7, 5, -11, 1, 5, -9}));
        System.out.println();

        System.out.println("Task 2");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println();

        System.out.println("Task 3");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println();

        System.out.println("Task 4");
        System.out.println(sumDigProd(new int[] {16, 28}));
        System.out.println(sumDigProd(new int[] {0}));
        System.out.println(sumDigProd(new int[] {1, 2, 3, 4, 5, 6}));
        System.out.println();

        System.out.println("Task 5");
        System.out.println(sameVowelGroup(new String[] {"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String[] {"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String[] {"hoops", "chuff", "bot", "bottom"}));
        System.out.println();

        System.out.println("Task 6");
        System.out.println(validateCard(1234567890123456));
        System.out.println(validateCard(1234567890123452));
        System.out.println();

        System.out.println("Task 7");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println();

        System.out.println("Task 8");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("password123"));
        System.out.println();

        System.out.println("Task 9");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println();

        System.out.println("Task 10");
        System.out.println(haxLattice(1));
        System.out.println(haxLattice(17));
        System.out.println(haxLattice(19));
        System.out.println(haxLattice(37));
        System.out.println();


        System.out.println();
    }


    public static String encrypt(String str) {
        int[] mass = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (i == 0)
                mass[i] = str.charAt(i);
            else
                mass[i] += str.charAt(i) - (str.charAt(i - 1));
        }
        return Arrays.toString(mass);
    }

    public static String decrypt(int[] mass) {
        char[] str = new char[mass.length];
        for (int i = 0; i < mass.length; i++) {
            if (i == 0) {
                str[i] = (char) mass[i];
            } else {
                str[i] = (char) (mass[i - 1] + mass[i]);
                mass[i] = mass[i - 1] + mass[i];
            }
        }
        return new String(str);
    }

    public static boolean canMove(String name, String start, String end) {
        char startChar = start.charAt(0);
        int startInt = Integer.parseInt(String.valueOf(start.charAt(1)));
        char endChar = end.charAt(0);
        int endInt = Integer.parseInt(String.valueOf(end.charAt(1)));
        if (startChar == endChar && startInt == endInt) return false;
        switch (name) {
            case "Pawn": {
                if (startChar == endChar && startInt == 2 && endInt == 4)
                    return true;
                return startChar == endChar && endInt == (startInt + 1);
            }
            case "Knight": {
                return (Math.abs(startChar - endChar) == 2 && Math.abs(startInt - endInt) == 1) || (Math.abs(startChar - endChar) == 1 && Math.abs(startInt - endInt) == 2);
            }
            case "Bishop": {
                return Math.abs(startChar - endChar) == Math.abs(startInt - endInt);
            }
            case "Rook": {
                return (startChar == endChar && startInt != endInt) || (startChar != endChar && startInt == endInt);
            }
            case "Queen": {
                if ((startChar == endChar && startInt != endInt) || (startChar != endChar && startInt == endInt))
                    return true;
                if (Math.abs(startChar - endChar) == Math.abs(startInt - endInt))
                    return true;
                break;
            }
            case "King": {
                return Math.abs(startChar - endChar) < 2 && Math.abs(startInt - endInt) < 2;
            }
            default:
                return false;
        }
        return false;
    }

    public static boolean canComplete(String str1, String str2) {
        char[] massstr1 = str1.toCharArray();
        int num = 0;
        for (char c : massstr1) {
            if (str2.indexOf(String.valueOf(c), num) != -1)
                num = str2.indexOf(String.valueOf(c), num) + 1;
            else
                return false;
        }
        return true;
    }

    public static int sumDigProd(int[] mass) {
        int sum = 0;
        for (int value : mass) {
            sum += value;
        }
        while (sum > 9) {
            int mult = 1;
            while (sum > 0) {
                mult *= sum % 10;
                sum /= 10;
            }
            sum = mult;
        }
        return sum;
    }

    public static String sameVowelGroup (String[] str) {
        String vowel = "aeiouyAEIOUY";
        StringBuilder first = new StringBuilder();
        String second = "";

        ArrayList <String> words = new ArrayList<>();
        Collections.addAll(words, str);

        for (int i = 0; i < words.get(0).length(); i++) {
            if (vowel.indexOf(words.get(0).charAt(i)) != -1)
                first.append(words.get(0).charAt(i));
        }

        for (int i = words.size() - 1; i >= 0; i--) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (vowel.indexOf(words.get(i).charAt(j)) != -1) {
                    second += words.get(i).charAt(j);
                }
            }
            for (int k = 0; k < second.length();) {
                if (first.toString().indexOf(second.charAt(k)) != -1)
                    k++;
                else {
                    words.remove(i);
                    second = "";
                }
            }
        }
        return String.valueOf(words);
    }

    public static boolean validateCard(long cardNum) {
        StringBuilder str= new StringBuilder();
        if ( Long.toString(cardNum).length()>= 14 && Long.toString(cardNum).length() <= 19) {
            // step 1
            long lastNum = cardNum%10;
            StringBuilder cardNumStr = new StringBuilder(Long.toString(cardNum/=10));
            // step 2
            cardNumStr.reverse();
            // step 3
            for (int i = 0; i< cardNumStr.length(); i++){
                if (i%2==0){
                    int c =Character.getNumericValue(cardNumStr.charAt(i))*2;
                    if(c>9){
                        String buf = Integer.toString(c);
                        str.append(Character.getNumericValue(buf.charAt(0)) + Character.getNumericValue(buf.charAt(1)));
                    }
                    else str.append(c);
                }
                else str.append(cardNumStr.charAt(i));
            }
            System.out.println(str);
            int sum=0;
            for (int i=0;i<str.length();i++)
                sum+=Character.getNumericValue(str.charAt(i));
            System.out.println(sum);
            System.out.println(lastNum);
            return lastNum == 10 - sum % 10;
        }
        return false;
    }

    public static String numToEng(int num) {
        String strnum = "";
        if (num == 0) return "zero";
        // сотни
        switch (num / 100) {
            case 1: {
                strnum += "one hundred ";
                break;
            }
            case 2: {
                strnum += "two hundred ";
                break;
            }
            case 3: {
                strnum += "three hundred ";
                break;
            }
            case 4: {
                strnum += "four hundred ";
                break;
            }
            case 5: {
                strnum += "five hundred ";
                break;
            }
            case 6: {
                strnum += "six hundred ";
                break;
            }
            case 7: {
                strnum += "seven hundred ";
                break;
            }
            case 8: {
                strnum += "eight hundred ";
                break;
            }
            case 9: {
                strnum += "nine hundred ";
                break;
            }
        }
        // десятки до 20
        switch (num / 10 % 10) {
            case 1: {
                switch (num % 10) {
                    case 0: {
                        strnum += "ten";
                        return strnum;
                    }
                    case 1: {
                        strnum += "eleven";
                        return strnum;
                    }
                    case 2: {
                        strnum += "twelve";
                        return strnum;
                    }
                    case 3: {
                        strnum += "thirteen";
                        return strnum;
                    }
                    case 4: {
                        strnum += "fourteen";
                        return strnum;
                    }
                    case 5: {
                        strnum += "fifteen";
                        return strnum;
                    }
                    case 6: {
                        strnum += "sixteen";
                        return strnum;
                    }
                    case 7: {
                        strnum += "seventeen";
                        return strnum;
                    }
                    case 8: {
                        strnum += "eighteen";
                        return strnum;
                    }
                    case 9: {
                        strnum += "nineteen";
                        return strnum;
                    }
                }
            }
            // десятки после 20
            case 2: {
                strnum += "twenty ";
                break;
            }
            case 3: {
                strnum += "thirty ";
                break;
            }
            case 4: {
                strnum += "forty ";
                break;
            }
            case 5: {
                strnum += "fifty ";
                break;
            }
            case 6: {
                strnum += "sixty ";
                break;
            }
            case 7: {
                strnum += "seventy ";
                break;
            }
            case 8: {
                strnum += "eighty ";
                break;
            }
            case 9: {
                strnum += "ninety ";
                break;
            }
        }
        // единицы
        switch (num % 10) {
            case 1: {
                strnum += "one";
                break;
            }
            case 2: {
                strnum += "two";
                break;
            }
            case 3: {
                strnum += "three";
                break;
            }
            case 4: {
                strnum += "four";
                break;
            }
            case 5: {
                strnum += "five";
                break;
            }
            case 6: {
                strnum += "six";
                break;
            }
            case 7: {
                strnum += "seven";
                break;
            }
            case 8: {
                strnum += "eight";
                break;
            }
            case 9: {
                strnum += "nine";
                break;
            }
        }
        return strnum;
    }

    public static String numToRus(int num) {
        String strnum = "";
        if (num == 0) return "ноль";
        // сотни
        switch (num / 100) {
            case 1: {
                strnum += "сто ";
                break;
            }
            case 2: {
                strnum += "двести ";
                break;
            }
            case 3: {
                strnum += "триста ";
                break;
            }
            case 4: {
                strnum += "четыреста ";
                break;
            }
            case 5: {
                strnum += "пятьсот ";
                break;
            }
            case 6: {
                strnum += "шестьсот ";
                break;
            }
            case 7: {
                strnum += "семьсот ";
                break;
            }
            case 8: {
                strnum += "восемьсот ";
                break;
            }
            case 9: {
                strnum += "девятьсот ";
                break;
            }
        }
        // десятки до 20
        switch (num / 10 % 10) {
            case 1: {
                switch (num % 10) {
                    case 0: {
                        strnum += "десять";
                        return strnum;
                    }
                    case 1: {
                        strnum += "одиннадцать";
                        return strnum;
                    }
                    case 2: {
                        strnum += "двенадцать";
                        return strnum;
                    }
                    case 3: {
                        strnum += "тринадцать";
                        return strnum;
                    }
                    case 4: {
                        strnum += "четырнадцать";
                        return strnum;
                    }
                    case 5: {
                        strnum += "пятнадцать";
                        return strnum;
                    }
                    case 6: {
                        strnum += "шестнадцать";
                        return strnum;
                    }
                    case 7: {
                        strnum += "семнадцать";
                        return strnum;
                    }
                    case 8: {
                        strnum += "восемьнадцать";
                        return strnum;
                    }
                    case 9: {
                        strnum += "двадцать";
                        return strnum;
                    }
                }
            }
            // десятки после 20
            case 2: {
                strnum += "двадцать ";
                break;
            }
            case 3: {
                strnum += "тридцать ";
                break;
            }
            case 4: {
                strnum += "сорок ";
                break;
            }
            case 5: {
                strnum += "пятьдесят ";
                break;
            }
            case 6: {
                strnum += "шестьдесят ";
                break;
            }
            case 7: {
                strnum += "семьдесят ";
                break;
            }
            case 8: {
                strnum += "восемьдесят ";
                break;
            }
            case 9: {
                strnum += "девяносто ";
                break;
            }
        }
        // единицы
        switch (num % 10) {
            case 1: {
                strnum += "один";
                break;
            }
            case 2: {
                strnum += "два";
                break;
            }
            case 3: {
                strnum += "три";
                break;
            }
            case 4: {
                strnum += "четыре";
                break;
            }
            case 5: {
                strnum += "пять";
                break;
            }
            case 6: {
                strnum += "шесть";
                break;
            }
            case 7: {
                strnum += "семь";
                break;
            }
            case 8: {
                strnum += "восемь";
                break;
            }
            case 9: {
                strnum += "девять";
                break;
            }
        }
        return strnum;
    }

    public static String getSha256Hash(String uncrypted) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(uncrypted.getBytes("UTF-8")); //Создаем массив с байтами строки
            StringBuffer hexString = new StringBuffer();

            for (int k = 0; k < hash.length; k++) { //В циксле при поомщи метода переводим значение в хэш
                String hex = Integer.toHexString(0xff & hash[k]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return (hexString.toString()); //Выводим хэшированную строку
        }
        catch(Exception ex){ //Проверка на ошибки хэширования
            throw new RuntimeException(ex);
        }
    }

    public static String correctTitle(String title) {
        String[] text = title.toLowerCase().split(" ");
        title = "";
        String pos = "";
        for (int i = 0; i < text.length; i++) {
            while (text[i].contains("-")) {
                pos += text[i].indexOf("-") + " ";
                text[i] = text[i].substring(0, text[i].indexOf("-")) + " " + text[i].substring(text[i].indexOf("-") + 1);
                System.out.println(text[i].substring(text[i].indexOf("-") + 1));
            }
            if (text[i].equals("in") || text[i].equals("of") || text[i].equals("and") || text[i].equals("the"))
                title += text[i].toLowerCase() + " ";
            else
                title += text[i].substring(0, 1).toUpperCase() + text[i].substring(1) + " ";
        }
        return title;
    }

    public static String haxLattice(int n){
        int num = 1;
        int i = 1;
        String res="";
        String str2="";
        while (n>num) {
            i++;
            num = 3 * i * (i - 1) + 1;
        }
        int l = i;
        // верхняя половина
        if (n != num)
            res = "invalid";
        else {
            while (l < i * 2 - 1) {
                for (int a = 0; a < i * 2 - 1 - l; a++)
                    res += "  ";
                for (int b = 0; b < l; b++)
                    res += " o  ";
                res += "\n";
                l++;
            }
            // нижняя половина
            while (l >= i) {
                for (int a = 0; a < i * 2 - 1 - l; a++)
                    res += "  ";
                for (int b = l; b > 0; b--)
                    res += " o  ";
                res += "\n";
                l--;
            }
        }
        return res;
    }
}
