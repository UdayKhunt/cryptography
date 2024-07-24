package Cryptography;

import java.util.Scanner;

public class addTwoPointsECC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 1;
        int b = 1;
        System.out.print("Enter Prime number: ");
        int p = in.nextInt();
        System.out.print("Enter coordinates for P: ");
        int px = in.nextInt();
        int py = in.nextInt();
        System.out.print("Enter coordinates for Q: ");
        int qx = in.nextInt();
        int qy = in.nextInt();

        if (px == qx && py == qy){
            System.out.print("Enter a: ");
            a = in.nextInt();
            System.out.println("The two given points are equal");
            double g = (3*Math.pow(px,2)+a);
            double h = 2 * py;
            if (g % h == 0){
                double lambda = g / h;
                lambda %= p;
                if (lambda < 0) lambda = p - (-lambda % p);
                double x3 = Math.pow(lambda , 2) - px - qx;
                x3 %= p;
                if (x3 < 0) x3 = p - (-x3 % p);
                double y3 = lambda*(px - x3) - py;
                y3 = y3 % p;
                if (y3 < 0) y3 = p - (-y3 % p);
                System.out.print("(x3,y3): " + (int)x3 + " " + (int)y3);
            }
            else{
                h = compute((int)h , p);
                double lambda = g*h;
                lambda %= p;
                if (lambda < 0) lambda = p - (-lambda % p);
                double x3 = Math.pow(lambda , 2) - px - qx;
                x3 %= p;
                if (x3 < 0) x3 = p - (-x3 % p);
                double y3 = lambda*(px - x3) - py;
                y3 = y3 % p;
                if (y3 < 0) y3 = p - (-y3 % p);
                System.out.print("(x3,y3): " + (int)x3 + " " + (int)y3);
            }
        }
        else{
            double g = (qy - py);
            double h = (qx - px);
            double lambda;
            if (g % h == 0)
                lambda = g / h;
            else{
                h = compute((int)h , p);
                lambda = g*h;

            }
            if (lambda < 0) lambda = p - (-lambda % p);
            double x3 = Math.pow(lambda , 2) - px - qx;
            x3 %= p;
            if (x3 < 0) x3 = p - (-x3 % p);
            double y3 = lambda*(px - x3) - py;
            y3 = y3 % p;
            if (y3 < 0) y3 = p - (-y3 % p);

            System.out.print("(x3,y3): " + (int)x3 + " " + (int)y3);
        }
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
