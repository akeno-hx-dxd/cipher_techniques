// Caesar_cipher_rules
// Requirements : plaintext,key,ciphertext
// Encryption steps : change each letter in plaintext with a letter at (current_letter's position + key) position 
// Decryption steps : change each letter in ciphertext with a letter at (current_letter's position - key) position 

import java.util.Scanner;

class CaeserCipher {
    public static String alphabets = "zabcdefghijklmnopqrsuvtwxy";

    public static void main( String[] args){
        System.out.println("Caeser Cipher Technique.");
        try(Scanner scan = new Scanner(System.in)){
            System.out.print("Menu:\n\t1.Encrypt Text.\n\t2.Decrypt Text.\n\t3.Exit Program.\nMake a Selection: ");
            int OrderNum = scan.nextInt();
            System.out.println("");
            switch(OrderNum){
                case 1 : {
                    System.out.println("Selection - Encrypt.");
                    System.out.print("Input the plaintext: ");
                    //java things 
                    scan.nextLine();
                    String pt = scan.nextLine();
                    System.out.print("Input the key: ");
                    int key = scan.nextInt();
                    //encrypt
                    String encryptedText = encryptText(pt, key);
                    System.out.println("Encrypted text: "+encryptedText);
                }
                break;
                case 2 : {
                    System.out.println("Selection - Decrypt.");
                    System.out.print("Input the ciphertext: ");
                    scan.nextLine();
                    String ct = scan.nextLine();
                    System.out.print("Input the key: ");
                    int key = scan.nextInt();
                    //dencrypt
                    String decryptedText = decryptText(ct, key);
                    System.out.println("Decrypted text: "+decryptedText);
                }
                break;
                case 3 : {
                    System.out.println("Selection - Exit.\nExiting...");
                    System.exit(0);
                }
                break;
                default : {
                    System.out.println("Selection - Invalid.\nExiting...");
                    System.exit(1);
                }
            }
        }catch(Exception err){
            System.err.println(err);
        }
    }

    public static String encryptText(String pt, int key){
        String ct="";
        for(char c: pt.toLowerCase().toCharArray()){
            if(c == ' ' || c == '_'){
                ct += c;
            }else{
                for(int i=0; i<alphabets.length(); i++){
                    if(alphabets.toCharArray()[i] == c){
                        ct += alphabets.toCharArray()[(i+key)%26];
                    }
                }
            }
            
        }
        return ct;
    } 

    public static String decryptText(String ct, int key){
        String pt="";
        for(char c: ct.toLowerCase().toCharArray()){
            if(c == ' ' || c == '_'){
                pt += c;
            }else{
                for(int i=0; i<alphabets.length(); i++){
                    if(alphabets.toCharArray()[i] == c){
                        pt += alphabets.toCharArray()[(i-key)%26];
                    }
                }
            }
        }
        return pt;
    }
}

// Note: Read this

// The above code works well for a single word, but fails when a sentence is passed !
// Why ?
// Because, a sentence contains, space's, comma's, symbol's etc, which isn't accounted in our current logic.
// Hence, this program, isn't at a level where we can freely encrypt and decrypt large messages. 
// What you can do, Yes you 🫵🏽, you take a step forward in your programming journey,
// By contributing to this program, which others will use to encrypt and decrypt messages and the cool thing is you contributed to it(your names gonna be on the contributers list and github and on the frontend thats gonna be developed soon 🤝🏽 !)
// How to contribute (It's damn simple),
// 1. Create a github account.
// 2. Fork my repository at https://github.com/akeno-hx-dxd/cipher_techniques.git. 
// 3. Clone the forked Repository. 
// 4. Work on the remaining logic. 
// 5. Open a PR once done

// What you gain !
// Github and Git skills
// Real world coding experiance (Yay, this is how companies make programmers write, share and Version control their codes through git-github 😁.)
// Seeing your code in action. (I am gonna deploy the Backend [The logic part, java code on aws] and the Frontend part in React, yay we are gonna deploy it to ...)
// and more ... and more ... 

// Requirements
// A bit of effort (yep, you need to watch those youtube videos and learn how to use git and github)
// Trail and Error (just, try writing that first shitty 💩 code that might or might not work and find out the cause until you get it 💡 !!)
// Sayonara, See you soon 👻 on the contributers list 📜. 
