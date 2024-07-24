package Cryptography;

import java.util.Arrays;
import java.util.Scanner;

public class PlayfairCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Key: ");
        String str = in.next();

        char[][] arr = new char[5][5];

        int i = 0;
        int j = 0;
        int[] t = new int[26];
        for(char ch : str.toCharArray()){
            if (j == 5){
                j = 0;
                i++;
            }

            if (t[ch - 'a'] == 0){
                arr[i][j++] = ch;
                t[ch - 'a']++;
            }
        }

        for(int k = 0 ; k < 26 ; ++k){
            if(j == 5) {
                j = 0;
                i++;
            }
            if (k == 'i' - 'a' && t[k+1] != 0) continue;

            if (k == 'j' - 'a' && t[k-1] != 0) continue;

            if (t[k] == 0){
                arr[i][j++] = (char)('a' + k);
                t[k]++;
            }
        }

        for(char[] a : arr){
            System.out.println(Arrays.toString(a));
        }


        System.out.print("Enter String: ");
        str = formPairs(in.next());

        if (str.length() % 2 == 1) str += "x";

        StringBuilder ans = new StringBuilder();

        for(int z = 0 ; z < str.length() ; z += 2){
            int[] nums = coordinates(arr , str.charAt(z));
            int r1 = nums[0];
            int c1 = nums[1];

            int[] nums1 = coordinates(arr , str.charAt(z+1));
            int r2 = nums1[0];
            int c2 = nums1[1];

            if (r1 == r2) ans.append(sameRow(r1, c1, c2, arr));
            else if (c1 == c2) ans.append(sameCol(r1, r2, c1, arr));
            else ans.append(diffRowDiffCol(r1, r2, c1, c2, arr));
        }

        System.out.println("Encrypted String: " + ans);

    }

    static String formPairs(String str){

        for(int i = 0 ; i < str.length() ; ++i){
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)){
                str = str.substring(0,i+1) + "x" + str.substring(i+1);
            }
        }

        return str;
    }

    static String sameRow(int r , int c1 , int c2, char[][] arr){
        char one = arr[r][(c1 + 1) % 5];
        char two = arr[r][(c2 + 1) % 5];
        return ""+one+two;
    }

    static String sameCol(int r1 , int r2 , int c , char[][] arr){
        char one = arr[(r1 + 1)%5][c];
        char two = arr[(r2 + 1)%5][c];
        return ""+one+two;
    }

    static String diffRowDiffCol(int r1 , int r2 , int c1 , int c2, char[][] arr){
        char one = arr[r1][c2];
        char two = arr[r2][c1];
        return ""+one+two;
    }

    static int[] coordinates(char[][] arr , char a){
        for(int i = 0 ; i < 5 ; ++i){
            for(int j = 0 ; j < 5 ; ++j){
                if (arr[i][j] == a) return new int[]{i , j};
            }
        }

        return new int[]{-1,-1};
    }


}
