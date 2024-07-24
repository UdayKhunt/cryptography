package Cryptography;

import java.util.Scanner;
public class Caesar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Message: ");
        String str = in.next();

        System.out.println("Key is set to 3 for caesar cipher");
        int k = 3;

        StringBuilder s = new StringBuilder();

        for(char ch : str.toCharArray()){
            if (ch + k > 'z'){
                s.append((char)('a' + ((ch + k) - 'z') - 1));
            }
            else s.append((char)(ch + k));
        }
        String enc = s.toString().toUpperCase();
        System.out.print("Encrypted text: ");
        System.out.println(enc);

        String dec = enc.toLowerCase();
        StringBuilder ss = new StringBuilder();
        for(char ch : dec.toCharArray()){
            if (ch - k < 'a'){
                ss.append((char)('z' - ('a' - (ch - k)) + 1));
            }
            else{
                ss.append((char)(ch - k));
            }
        }
        System.out.print("Decrypted text: ");
        System.out.println(ss);

    }
}
