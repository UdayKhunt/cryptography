package Cryptography;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class aesKeyExpansion {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        System.out.print("Enter a key of 128 bits / 16 bytes: ");
        //taking string input (key)
        String key = in.next();
        String newKey = "";
        //converting string input to corresponding hexadecimal value
        for(int i = 0 ; i < key.length() ; ++i){
            newKey += Integer.toHexString((int)key.charAt(i));
        }
        key = newKey;
        //pre defined parameters
        String[][] aes_S_BOX = {
                //0     1    2      3     4    5     6     7      8    9     A      B    C     D     E     F
                {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
                {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
        };

        String[] R_C_O_N = {
                "01000000",
                "02000000",
                "04000000",
                "08000000",
                "10000000",
                "20000000",
                "40000000",
                "80000000",
                "1b000000",
                "36000000",
        };

        Map<String,String> map = new HashMap<>();

        map.put("0" , "0000");
        map.put("1" , "0001");
        map.put("2" , "0010");
        map.put("3" , "0011");
        map.put("4" , "0100");
        map.put("5" , "0101");
        map.put("6" , "0110");
        map.put("7" , "0111");
        map.put("8" , "1000");
        map.put("9" , "1001");
        map.put("a" , "1010");
        map.put("b" , "1011");
        map.put("c" , "1100");
        map.put("d" , "1101");
        map.put("e" , "1110");
        map.put("f" , "1111");

        map.put("0000" , "0");
        map.put("0001" , "1");
        map.put("0010" , "2");
        map.put("0011" , "3");
        map.put("0100" , "4");
        map.put("0101" , "5");
        map.put("0110" , "6");
        map.put("0111" , "7");
        map.put("1000" , "8");
        map.put("1001" , "9");
        map.put("1010" , "a");
        map.put("1011" , "b");
        map.put("1100" , "c");
        map.put("1101" , "d");
        map.put("1110" , "e");
        map.put("1111" , "f");

        //subkey 0
        String w0 = key.substring(0 , 8);
        String w1 = key.substring(8 , 16);
        String w2 = key.substring(16 , 24);
        String w3 = key.substring(24);

        String[] keys = new String[44];
        keys[0] = w0;
        keys[1] = w1;
        keys[2] = w2;
        keys[3] = w3;
        System.out.println();
        //printing subkeys
        for(int i = 4 ; i < 41 ; i += 4){
            System.out.println("-----------------key" + i/4 + "-----------------------\n");

            String g = computeG(keys[i-1] , aes_S_BOX , R_C_O_N , (i / 4) - 1 , map);
            String t1 = xor(g , keys[i - 4] , map);
            System.out.println("w" + ((i)) + ": " + t1);
            String t2 = xor(t1 , keys[i - 3] , map);
            System.out.println("w" + ((i+1)) + ": "  + t2);
            String t3 = xor(t2 , keys[i - 2] , map);
            System.out.println("w" + ((i+2)) + ": "  + t3);
            String t4 = xor(t3 , keys[i - 1] , map);
            System.out.println("w" + ((i+3)) + ": "  + t4);

            keys[i] = t1;
            keys[i + 1] = t2;
            keys[i + 2] = t3;
            keys[i + 3] = t4;


            System.out.println("\n");
        }
    }

    //computing g function
    static String computeG(String str , String[][] aesS_BOX, String[] R_C_O_N , int roundNumber , Map<String , String> map){
        //left shift once
        String ans = str.substring(2) + str.substring(0 , 2);

        int start = 0;
        int end = 2;

        String temp = "";

        for(int i = 0 ; i < 4 ; ++i){
            String s = ans.substring(start , end);
            int[] arr = getIndex(s);
            // refer AES s-box
            String value = aesS_BOX[arr[0]][arr[1]];
            temp += value;

            start += 2;
            end += 2;
        }

        //xor with r_constant
        String d = xor(temp , R_C_O_N[roundNumber], map);

        return d;
    }
    // computing index for aes s_box
    static int[] getIndex(String str){
        int[] ans = new int[2];
        if (str.charAt(0) >= 'a') ans[0] = str.charAt(0) - 'a' + 10;
        else ans[0] = str.charAt(0) - '0';

        if (str.charAt(1) >= 'a') ans[1] = str.charAt(1) - 'a' + 10;
        else ans[1] = str.charAt(1) - '0';
        return ans;
    }
    // predefined xor function
    static String xor(String a , String b , Map<String , String> map){
            String b1 = "";
            String b2 = "";

            for(int i = 0 ; i < 8 ; ++i){
                b1 += map.get(a.charAt(i) + "");
                b2 += map.get(b.charAt(i) + "");
            }
            String ans = "";
            for(int i = 0 ; i < 32 ; ++i){
                if ((b1.charAt(i) == '1' && b2.charAt(i) == '1') || (b1.charAt(i) == '0' && b2.charAt(i) == '0')) ans += "0";
                else ans += "1";
            }
            String ans1 = "";
            int start = 0;
            int end = 4;
            for(int i = 0 ; i < 8 ; ++i){
                ans1 += map.get(ans.substring(start , end));
                start += 4;
                end += 4;
            }
            return ans1;
    }
}
