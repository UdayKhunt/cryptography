package Cryptography;

import java.util.Scanner;

public class elGamal {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Prime Number: ");
        int p = in.nextInt();
        System.out.print("Enter generator: ");
        int g = in.nextInt();
        System.out.print("Enter private key d: ");
        int d = in.nextInt();
        int e = mod(g , d , p);
        System.out.print("Enter Message to be sent: ");
        int m = in.nextInt();
        System.out.print("Enter k, a random integer: ");
        int k = in.nextInt();
        int y1 = mod(g , k , p);
        int y2 = m * mod(e , k, p) % p;
        int message = y2 * compute((int)Math.pow(y1 , d) , p) % p;
        System.out.println(message);
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

    static int compute(int e,  int phi) {
        e %= phi;
        int d = 0;
        while(true){
            if ((d * e) % phi == 1) return d;
            d++;
        }
    }

}
