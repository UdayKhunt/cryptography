package Cryptography;

import java.util.*;

public class euclid {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number:");
        int a = sc.nextInt();
        System.out.println("Enter the second number:");
        int b = sc.nextInt();
        int a1 = a;
        int b1 = b;

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        int gcd = a;
        System.out.println("GCD: " + gcd);

        int x1 = 1, x2 = 0, y1 = 0, y2 = 1;
        int t1 = a1;
        int t2 = b1;
        int quotient = 0;
        int rem = 0;
        while (t1 * x1 + t2 * y1 != gcd) {
            if (b1 != 0){
                quotient = a1 / b1;
                rem = a1 % b1;
            }

            a1  = b1;
            b1 = rem;
            int x = x1 - quotient * x2;
            int y = y1 - quotient * y2;
            x1 = x2;
            x2 = x;
            y1 = y2;
            y2 = y;
        }

        System.out.println("x: " + x1);
        System.out.println("y: " + y1);
    }
}