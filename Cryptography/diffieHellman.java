package Cryptography;

import java.util.Scanner;

public class diffieHellman {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a prime number P: ");
        int n = in.nextInt();
        if (!isPrime(n)) System.out.println("The given number is not prime");
        System.out.print("Enter generator of Prime number " + n + ": ");
        int g = in.nextInt();
        int[] arr = new int[n];
        boolean flag = true;
        for(int i = 0 ; i < n - 1; ++i){
            int t = mod2(g, i , n);
            if (++arr[t] == 2) {
                System.out.println("The given number is not generator of prime number " + n);
                flag = false;
                break;
            }
        }
        if (flag){
            System.out.print("Enter Private Key of A: ");
            int xa = in.nextInt();
            int ya = mod2(g,xa,n);
            System.out.print("Enter private key of B:");
            int xb = in.nextInt();
            int yb = mod2(g , xb , n);

            int key_from_A = mod2(yb,xa,n);
            int key_from_B = mod2(ya , xb , n);

            System.out.println("Key received by A: " + key_from_A);
            System.out.println("Key received by B: " + key_from_B);
        }
    }
    static boolean isPrime(int n){
        for(int i = 2 ; i * i < n ; ++i){
            if (n % i == 0) return false;
        }

        return true;
    }

    static int mod2(int a , int b , int c){
        int ans = 1;
        while(b-- != 0){
            ans = ans*a % c;
        }
        return ans;
    }


}
