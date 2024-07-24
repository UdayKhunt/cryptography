package Cryptography;
import java.util.*;
public class manInTheMiddle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter prime number: ");
        int p = in.nextInt();
        System.out.print("Enter generator: ");
        int g = in.nextInt();

        System.out.print("Enter Xa: ");
        int xa = in.nextInt();
        System.out.print("Enter Xb: ");
        int xb = in.nextInt();

        int PUa = mod(g , xa , p);
        System.out.println("PUa: " + PUa);
        int PUb = mod(g , xb , p);
        System.out.println("PUb: " + PUb);

        System.out.print("Enter xd1: ");
        int xd1 = in.nextInt();
        System.out.print("Enter xd2: ");
        int xd2 = in.nextInt();

        int PUqa = mod(g,xd1,p);
        int PUqb = mod(g,xd2,p);

        System.out.println("PUqa: " + PUqa);
        System.out.println("PUqb: " + PUqb);

        int KA = mod(PUqa , xa , p);
        int KB = mod(PUqb , xb , p);

        int KQA = mod(PUa , xd1 , p);
        int KQB = mod(PUb , xd2 , p);


        System.out.println("KA: " + KA);
        System.out.println("KQA: " + KQA);
        System.out.println("KB: " + KB);
        System.out.println("KQB: " + KQB);

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
