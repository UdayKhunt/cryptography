package Cryptography;

import java.util.*;
public class HillCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter PlainText: ");
        String str = in.next();
        System.out.print("Enter n: ");
        int n = in.nextInt();
        int u = str.length();

        System.out.print("Enter a key: ");
        String key = in.next();

        int[][] keyM = new int[n][n];

        int i = 0;
        int j = 0;

        int x = key.length();
        for(int k = 0 ; k < x ; ++k){
            if (j == n){
                i++;
                j = 0;
            }
            keyM[i][j] = key.charAt(k) - 'a';
            j++;
        }

        if (str.length() % 2 == 1) str += "a";

        int t = str.length() / 2;
        int y = 0;
        String ans = "";
        for(int d = 0 ; d < t ; ++d){
            int[][] v = new int[2][1];
            v[0][0] = str.charAt(y++) - 'a';
            v[1][0] = str.charAt(y++) - 'a';

            int[][] k = matrixMultiply(keyM , v , 2,2,2,1);
            k[0][0] %= 26;
            k[1][0] %= 26;

            ans += (char)(k[0][0] + 'a');
            ans += (char)(k[1][0] + 'a');
        }

        System.out.println("Encrypted text: " + ans);


        int det = keyM[0][0] * keyM[1][1] - keyM[1][0] * keyM[0][1];

        det %= 26;
        int inv = 0;
        for(int m = 1 ; m != Integer.MAX_VALUE ; ++m){
            if ((det * m) % 26 == 1) {
                inv = m;
                break;
            }
        }

        int b = keyM[0][0];
        keyM[0][0] = keyM[1][1];
        keyM[1][1] = b;

        keyM[1][0] = (-keyM[1][0]) + 26;
        keyM[0][1] = (-keyM[0][1]) + 26;

        for(int d = 0 ; d < 2 ; ++d){
            for(int h = 0 ; h < 2 ; ++h){
                keyM[d][h] *= inv;
            }
        }
        String ans2 = "";
        y = 0;
        for(int d = 0 ; d < t ; ++d){
            int[][] v = new int[2][1];
            v[0][0] = ans.charAt(y++) - 'a';
            v[1][0] = ans.charAt(y++) - 'a';

            int[][] k = new int[2][1];

            k = matrixMultiply(keyM , v , 2,2,2,1);
            k[0][0] %= 26;
            k[1][0] %= 26;

            ans2 += (char)(k[0][0] + 'a');
            ans2 += (char)(k[1][0] + 'a');
        }

        System.out.println("Decrypted text: " + ans2.substring(0,u));

    }

    static int[][] matrixMultiply(int[][] a , int[][] b , int r1 , int c1 , int r2, int c2){
        int[][] mat = new int[r1][c2];

        for(int i = 0 ; i < r1 ; ++i){
            for(int j = 0 ; j < c2 ; ++j){
                for(int k = 0 ; k < r2 ; ++k){
                    mat[i][j] += a[i][k]*b[k][j];
                }
            }
        }

        return mat;
    }
}
