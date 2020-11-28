package cat.esteve.atc.utils;

public class Cryptography {
    public static String method01_code(String s, int seed) {
        String res = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res += String.valueOf((char) (c+seed));
        }
        return res;
    }

    public static String method01_decode(String s, int seed) {
        String res = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res += String.valueOf((char) (c-seed));
        }
        return res;
    }
}
