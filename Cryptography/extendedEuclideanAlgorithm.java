package Cryptography;

import java.util.Arrays;
import java.util.Scanner;

public class extendedEuclideanAlgorithm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = in.nextInt();
        System.out.print("Enter second number: ");
        int b = in.nextInt();

        int x1 = 1;
        int x2 = 0;
        int y1 = 0;
        int y2 = 1;
        int x;
        int y;

        int ans;
        int[] arr = new int[2];
        while(true){
            if (b == 0) {
                ans = a;
                arr[0] = x1;
                arr[1] = y1;
                break;
            }
            int q = a / b;
            int r = a % b;
            a = b;
            b = r;

            x = x1 - q*x2;
            y = y1 - q*y2;

            x1 = x2;
            x2 = x;

            y1 = y2;
            y2 = y;
        }

        System.out.println(ans);
        System.out.println(Arrays.toString(arr));
    }
}
