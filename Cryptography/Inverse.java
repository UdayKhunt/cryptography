package Cryptography;

public class Inverse {
    public static void main(String[] args) {
        System.out.println(compute(19,192));
        System.out.println();
    }

    private static int compute(int e,  int phi) {
        e %= phi;
        int d = 0;
        while(true){
            if ((d * e) % phi == 1) return d;
            d++;
        }
    }

    static int mod(int a , int b , int c){
        int ans = 1;
        while(b-- != 0){
            ans = ans * a % c;
        }
        return ans;
    }
}
