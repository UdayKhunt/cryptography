package Cryptography;

import java.util.*;
public class digitalSignatureStandard {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter P: ");
        int p = in.nextInt();
        System.out.print("Enter q: ");
        int q = in.nextInt();
        System.out.print("Enter h: ");
        int h = in.nextInt();
        System.out.print("Enter x: ");
        int x = in.nextInt();
        System.out.print("Enter H(M): ");
        int hm = in.nextInt();
        System.out.print("Enter k: ");
        int k = in.nextInt();

        int g = mod(h , (p - 1) / q , p);
        int y = mod(g , x , p);
        int r = mod(g , k , p) % q;
        int inv = compute(k , q);
        int s = inv*(hm + x*r) % q;

        int w = compute(s , q);
        int u1 = hm*w % q;
        int u2 = r*w % q;
        int v = (int)((Math.pow(g , u1)*Math.pow(y,u2) )% p) % q;
        System.out.println("v: " + v);
        System.out.println("r: " + r);
        if (v == r) System.out.println("Valid Signature");
        else System.out.println("Invalid Signature");

    }

    static int mod(int a , int b , int c){
        int ans = 1;
        while(b-- != 0){
            ans = ans * a % c;
        }
        return ans;
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
