package Cryptography;

import java.util.*;
public class elgamalBasedDSA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Prime Number: ");
        int p = in.nextInt();
        System.out.print("Enter Generator: ");
        int g = in.nextInt();

        System.out.print("Select d: ");
        int d = in.nextInt();

        int e = mod(g , d , p);
        System.out.print("Enter H(M): ");
        int m = in.nextInt();
        System.out.print("Select k: ");
        int k = in.nextInt();

        int y1 = mod(g , k , p);
        int inv = compute(k , p - 1);
        int y2 = inv*(m - d*y1) % (p - 1);
        if (y2 < 0) y2 = (p-1) - (-y2 % (p - 1));
        int v1 = mod(g , m , p);
        int v2 = (int)(Math.pow(e , y1) * Math.pow(y1 , y2)) % p;
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        if (v1 == v2) System.out.println("Valid Signature");
        else System.out.println("Invalid Signature");
    }
    static int mod(int a , int b , int c){
        if (b == 0) return 1;
        if (b == 1) return a % c;
        b--;

        int t = a % c;
        while(b != 0){
            b--;
            t = (t*a) % c;
        }

        return t;
    }

    private static int compute(int e,  int phi) {
        e %= phi;
        int d = 0;
        while(true){
            if ((d * e) % phi == 1) return d;
            d++;
        }
    }
}