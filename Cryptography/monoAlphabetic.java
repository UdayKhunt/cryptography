package Cryptography;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class monoAlphabetic {
    public static void main(String[] args) {
        Map<Character,Character> map = new HashMap<>();
        Map<Character,Character> pam = new HashMap<>();

        char[] a = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        char[] b = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
                'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
                'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' };

        for(int i = 0 ; i < 26 ; ++i){
            map.put(a[i] , b[i]);
            pam.put(b[i] , a[i]);
        }

        Scanner in = new Scanner(System.in);
        System.out.print("Enter Plain text: ");
        String str = in.next();

        StringBuilder s = new StringBuilder();
        for(char ch : str.toCharArray()){
            if (!map.containsKey(ch)) s.append(ch);
            else s.append(map.get(ch));
        }
        System.out.println("Encrypted Text: " + new String(s));

        String t = new String(s);
        StringBuilder v = new StringBuilder();

        for(char ch : t.toCharArray()){
            if (!pam.containsKey(ch)) v.append(ch);
            else v.append(pam.get(ch));
        }

        System.out.println("Decrypted Text: " + new String(v));

    }
}
