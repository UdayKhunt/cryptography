package Cryptography;

import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Select two Prime numbers: ");
        int p = in.nextInt();
        int q = in.nextInt();
        int n = p * q;
        int phi_n = (p-1)*(q-1);

        int e = 0;
        for(int i = 2 ; i < phi_n ; ++i){
            if (gcd(i , phi_n) == 1){
                e = i;
                break;
            }
        }
        int d = compute(e, phi_n);
        System.out.print("Enter number: ");
        int M = in.nextInt();

        int c = mod(M , e , n);
        int D = mod(c , d , n);

        System.out.println("Public key: " + e + " " + n);
        System.out.println("Private key: " + d + " " + n);

        System.out.println("Original message was: " + M);
        System.out.println("Decrypted message was " + D);
    }

    private static int compute(int e,  int phi) {
        e %= phi;
        int d = 0;
        while(true){
            if ((d * e) % phi == 1) return d;
            d++;
        }
    }

    static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b , a % b);
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
}
