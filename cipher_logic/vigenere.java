public class vigenere {
    static char[][] matrix = new char[26][26];
    static final char[] alphabets = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'
    };
    public static void main(String[] args) {
        
        for (int i = 0; i < 26; i++) {
            int head = i;
            for (int j = 0; j < 26; j++) {
                matrix[i][j] = alphabets[(head % 26)];
                head++;
            }
        }

        String pt = "batman";
        String key = "dark";
        String enc = encrypt(pt, key);

        System.out.println("Plaintext: "+ pt);
        System.out.println("Key: "+ key);
        System.out.println("Encrypted String: " + enc);

        String dec = decrypt(enc, key);

        System.out.println("Decrypted String: " + dec);
    }
    
    public static String encrypt(String plaintext, String key) {
        StringBuilder encrypted = new StringBuilder();

        int keyIndex = 0;
        
        for (int i = 0; i < plaintext.length(); i++) {
            char charPt = plaintext.charAt(i);
            int textIndex = charPt - 'a';
            int keyCharIndex = key.charAt(keyIndex % key.length()) - 'a'; 
            char encryptedChar = matrix[keyCharIndex][textIndex];
            encrypted.append(encryptedChar);
            keyIndex++;
        }
        
        return encrypted.toString();
    }
    
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyIndex = 0;
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char CharPt = ciphertext.charAt(i);
            int keyCharIndex = key.charAt(keyIndex % key.length()) - 'a';
            int originalIndex = -1;
                for (int j = 0; j < 26; j++) {
                    if (matrix[keyCharIndex][j] == CharPt) {
                        originalIndex = j;
                        break;
                    }
                }
                
                char decryptedChar = alphabets[originalIndex];
                decrypted.append(decryptedChar);
                keyIndex++;
        }
        return decrypted.toString();
    }
}