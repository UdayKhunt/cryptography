package Cryptography;
import java.util.*;
public class SDES {
    public static void main(String[] args) {
        int[] p10 = {3,5,2,7,4,10,1,9,8,6};
        int[] p8 = {6,3,7,4,8,5,10,9};
        System.out.print("Enter 10 bit key: ");
        Scanner in = new Scanner(System.in);
        String key = in.next();
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0 ; i < 10 ; ++i){
            sb1.append(key.charAt(p10[i] - 1));
        }
        key = sb1.toString();
        String a = key.substring(0 , 5);
        String b = key.substring(5);
        String ls1a = a.substring(1) + a.charAt(0);
        String ls1b = b.substring(1) + b.charAt(0);

        String combine = ls1a + ls1b;
        System.out.print("Key1: ");
        for(int i = 0 ; i < 8 ; ++i){
            System.out.print(combine.charAt(p8[i] - 1));
        }
        String ls2a = ls1a.substring(2) + ls1a.substring(0 , 2);
        String ls2b = ls1b.substring(2) + ls1b.substring(0 , 2);
        combine = ls2a + ls2b;
        System.out.println();
        System.out.print("Key2: ");
        for(int i = 0 ; i < 8 ; ++i){
            System.out.print(combine.charAt(p8[i] - 1));
        }
    }
}