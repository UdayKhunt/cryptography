package Cryptography;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class simplifiedDES {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] p10 = {3,5,2,7,4,10,1,9,8,6};
        int[] p8 = {6,3,7,4,8,5,10,9};
        int[] ip = {2,6,3,1,4,8,5,7};
        int[] e_p = {4,1,2,3,2,3,4,1};
        int[] ip_1 = {4,1,3,5,7,2,8,6};
        int[] p4 = {2,4,3,1};
        int[][] s0 = {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
        int[][] s1 = {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};

        System.out.print("Enter Plain text: ");
        int[] plain = new int[8];

        for(int i = 0 ; i < 8 ; ++i){
            plain[i] = in.nextInt();
        }

        System.out.print("Enter Key: ");

        int[] key = new int[10];
        for(int i = 0 ; i < 10 ; ++i){
            key[i] = in.nextInt();
        }
        // using p10
        int[] key_t = key.clone();
        for(int i = 0 ; i < 10 ; ++i){
            key[i] = key_t[p10[i] - 1];
        }
        // breaking into two halves
        int[] t1 = Arrays.copyOfRange(key,0,5);
        int[] t2 = Arrays.copyOfRange(key,5,10);

        //circular left shift by one bit
        leftShift(t1, 1);
        leftShift(t2 , 1);

        for(int i = 0 ; i < 5 ; ++i) key[i] = t1[i];
        for(int i = 0 ; i < 5 ; ++i) key[i+5] = t2[i];

        //applying p8
        int[] k1 = new int[8];
        key_t = key.clone();
        for(int i = 0 ; i < 8 ; ++i){
            k1[i] = key_t[p8[i] - 1];
        }

        //circular left shift by 2 bits
        leftShift(t1,2);
        leftShift(t2,2);


        for(int i = 0 ; i < 5 ; ++i) key[i] = t1[i];
        for(int i = 0 ; i < 5 ; ++i) key[i+5] = t2[i];

        //applying p8
        int[] k2 = new int[8];
        for(int i = 0 ; i < 8 ; ++i){
            k2[i] = key[p8[i] - 1];
        }

        System.out.println("k1: "+Arrays.toString(k1));
        System.out.println("k2: " + Arrays.toString(k2));

        int[] plain_t = plain.clone();

        //applying ip
        for(int i = 0 ; i < 8 ; ++i){
            plain[i] = plain_t[ip[i]-1];
        }

        //breaking into two halves
        int[] l = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            l[i] = plain[i];
        }
        int[] r = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            r[i] = plain[i+4];
        }

        int[] new_r = new int[8];
        //applying EP on right half
        for(int i = 0 ; i < 8 ; ++i){
            new_r[i] = r[e_p[i] - 1];
        }

        //xor with k1
        for(int i = 0 ; i < 8 ; ++i){
            new_r[i] ^= k1[i];
        }

        Map<String,String> map = new HashMap<>();

        //for s0 and s1
        map.put("11","3");
        map.put("10","2");
        map.put("01","1");
        map.put("00","0");
        map.put("0","00");
        map.put("1","01");
        map.put("2","10");
        map.put("3","11");


        int[] left_new_r = new int[4];
        int[] right_new_r = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            left_new_r[i] = new_r[i];
        }

        for(int i = 0 ; i < 4 ; ++i){
            right_new_r[i] = new_r[i+4];
        }
        //for s0 and s1
        String[] a = new String[2];
        String[] b = new String[2];

        a[0] = left_new_r[0] + "" + left_new_r[3];
        a[1] = left_new_r[1] + "" + left_new_r[2];

        b[0] = right_new_r[0] + "" + right_new_r[3];
        b[1] = right_new_r[1] + "" + right_new_r[2];

        int str1 = s0[Integer.parseInt(map.get(a[0]))][Integer.parseInt(map.get(a[1]))];
        int str2 = s1[Integer.parseInt(map.get(b[0]))][Integer.parseInt(map.get(b[1]))];

        String str3 = map.get(str1 + "") + map.get(str2 + "");

        int[] p = new int[4];
        for(int i = 0 ; i < 4 ; ++i){
            p[i] = str3.charAt(i) - '0';
        }

        int[] p_temp = p.clone();
        //applying p4
        for(int i = 0 ; i < 4 ; ++i){
            p[i] = p_temp[p4[i] - 1];
        }
        //xor with left part after IP
        for(int i = 0 ; i < 4 ; ++i){
            p[i] ^= l[i];
        }

        int[] d = new int[8];
        for(int i = 0 ; i < 4 ; ++i){
            d[i] = r[i];
        }

        for(int i = 4 ; i < 8 ; ++i){
            d[i] = p[i-4];
        }
        //second round begins
        int[] l1 = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            l1[i] = d[i];
        }
        int[] r1 = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            r1[i] = d[i+4];
        }

        int[] new_r1 = new int[8];

        for(int i = 0 ; i < 8 ; ++i){
            new_r1[i] = r1[e_p[i] - 1];
        }

        for(int i = 0 ; i < 8 ; ++i){
            new_r1[i] ^= k2[i];
        }

        int[] left_new_r1 = new int[4];
        int[] right_new_r1 = new int[4];

        for(int i = 0 ; i < 4 ; ++i){
            left_new_r1[i] = new_r1[i];
        }

        for(int i = 0 ; i < 4 ; ++i){
            right_new_r1[i] = new_r1[i+4];
        }

        String[] a1 = new String[2];
        String[] b1 = new String[2];

        a1[0] = left_new_r1[0] + "" + left_new_r1[3];
        a1[1] = left_new_r1[1] + "" + left_new_r1[2];

        b1[0] = right_new_r1[0] + "" + right_new_r1[3];
        b1[1] = right_new_r1[1] + "" + right_new_r1[2];

        int str11 = s0[Integer.parseInt(map.get(a1[0]))][Integer.parseInt(map.get(a1[1]))];
        int str21 = s1[Integer.parseInt(map.get(b1[0]))][Integer.parseInt(map.get(b1[1]))];

        String str31 = map.get(str11 + "") + map.get(str21 + "");

        int[] p1 = new int[4];
        for(int i = 0 ; i < 4 ; ++i){
            p1[i] = str31.charAt(i) - '0';
        }

        int[] p_temp1 = p1.clone();

        for(int i = 0 ; i < 4 ; ++i){
            p1[i] = p_temp1[p4[i] - 1];
        }

        for(int i = 0 ; i < 4 ; ++i){
            p1[i] ^= l1[i];
        }

        int[] d1 = new int[8];
        for(int i = 0 ; i < 4 ; ++i){
            d1[i] = p1[i];
        }

        for(int i = 4 ; i < 8 ; ++i){
            d1[i] = r1[i-4];
        }

        int[] v = d1.clone();

        for(int i = 0 ; i < 8 ; ++i){
            v[i] = d1[ip_1[i] - 1];
        }

        System.out.print("Encrypted text: " + Arrays.toString(v));
    }
    //function to perform circular left shift
    static void leftShift(int[] arr , int value){
        int[] t = new int[value];

        for(int i = 0 ; i < value ; ++i){
            t[i] = arr[i];
        }

        for(int i = value ; i < arr.length ; ++i){
            arr[i - value] = arr[i];
        }

        for(int i = arr.length - value; i < arr.length ; ++i){
            arr[i] = t[i - arr.length + value];
        }

    }
}
