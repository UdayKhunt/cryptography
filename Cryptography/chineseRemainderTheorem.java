package Cryptography;

import java.util.Scanner;

public class chineseRemainderTheorem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of numbers: ");
        int n = in.nextInt();
        int M = 1;

        int[] num = new int[n];
        int[] rem = new int[n];

        System.out.print("Enter " + n + " numbers: ");
        for(int i = 0 ; i < n ; ++i){
            num[i] = in.nextInt();
            M *= num[i];
        }


        System.out.print("Enter " + n + " remainders: ");
        for(int i = 0 ; i < n ; ++i){
            rem[i] = in.nextInt();
        }

        int[] z = new int[n];

        for(int i = 0 ; i < n ; ++i){
            z[i] = M / num[i];
        }


        int[] y = new int[n];

        for(int i = 0 ; i < n ; ++i){
            y[i] = inverse(z[i] , num[i]);
        }


        int ans = 0;
        for(int i = 0 ; i < n ; ++i){
            ans += rem[i]*z[i]*y[i];
        }

        ans %= M;

        System.out.println(ans);

    }

    static int inverse(int z , int m){
        z %= m;

        for(int i = 1 ; i < Integer.MAX_VALUE ; ++i){
            if ((z * i) % m == 1) return i;
        }

        return 1;
    }
}
