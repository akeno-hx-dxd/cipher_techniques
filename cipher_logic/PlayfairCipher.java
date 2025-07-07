// Playfair cipher rules
// requirements: plaintext, key
// encryption steps : 
// pairing of letters
// letter substituition , paired letters in same raw, pick the letter on right, on same cloumn, pick the one below and if neither create a rectangle and horizontal opposite of each

import java.util.Scanner;

class PlayfairCipher {

    public static void main(String[] args){
        System.out.println("Playfair Cipher Technique.");
        try(Scanner scan = new Scanner(System.in)){
            System.out.print("Menu:\n\t1.Encrypt Text.\n\t2.Decrypt Text.\n\t3.Exit Program.\nMake a Selection: ");
            int OrderNum = scan.nextInt();
            System.out.println("");
            switch(OrderNum){
                case 1 : {
                    System.out.println("Selection - Encrypt.");
                    System.out.print("Input the plaintext: ");
                    scan.nextLine();
                    String pt = scan.nextLine().toUpperCase().replace('J', 'I');
                    System.out.print("Input key(Alphabetic): ");
                    String key = scan.next();
                    encryptPlayfairCipher(pt, key);
                }
                break;
                case 2 : {
                   System.out.println("Coming soon :)");
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
        }
    }

    public static void encryptPlayfairCipher(String pt, String key){
        // generating 5x5 grid
        char[][] grid = generateGrid(key);

        // pairing of plaintext
        StringBuilder modified_pt = new StringBuilder();
        char[] pt_arr = pt.toCharArray();
        int index = 0;
        while(index < pt_arr.length-1){
            if(pt_arr[index] != pt_arr[index+1]){
                modified_pt.append(pt_arr[index]);
                modified_pt.append(pt_arr[index+1]);
                index += 2;
            } else {
                modified_pt.append(pt_arr[index]);
                modified_pt.append('X');
                index += 1;
            }
        }

        if(pt_arr.length%2 == 1){
            modified_pt.append(pt_arr[pt_arr.length-1]);
            modified_pt.append('X');
        }

        // encrypt plaintext
        StringBuilder ct = new StringBuilder();
        for(int i=0; i<modified_pt.length()-1; i=i+2){
            char c1= modified_pt.charAt(i), c2 = modified_pt.charAt(i+1);

            // Pair position
            Pair<Integer, Integer> c1_position = searchElementInGrid(c1,grid);
            Pair<Integer, Integer> c2_position = searchElementInGrid(c2, grid);

            if(c1_position.getElement0() != -1 && c1_position.getElement1() != -1 && c2_position.getElement0() != -1 && c2_position.getElement1() != -1){
                int f_r = c1_position.getElement0();
                int f_c = c1_position.getElement1();
                int s_r = c2_position.getElement0();
                int s_c = c2_position.getElement1();

                if(f_r == s_r){
                    f_c = f_c < 4 ? f_c+1 : 0;
                    s_c = s_c < 4 ? s_c+1 : 0;
                } else if(f_c == s_c) {
                    f_r = f_r < 4 ? f_r+1 : 0;
                    s_r = s_r < 4 ? s_r+1 : 0;
                } else {
                    f_c = f_c + s_c;
                    s_c = f_c - s_c;
                    f_c = f_c - s_c; 
                }

                ct.append(grid[f_r][f_c]);
                ct.append(grid[s_r][s_c]);

            }else{
                System.err.println("Error in encryption.");
                System.exit(1);
            }    
        }
        for(char[] x : grid){
            for(char y : x){
                System.out.print(y+" ");
            }
            System.out.println();
        }
        System.out.println("PlainText: "+ modified_pt.toString());
        System.out.println("Key: "+key);
        System.out.println("Encrypted text: "+ ct.toString());
    }

    public static Pair<Integer, Integer> searchElementInGrid(char c, char[][] grid){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(c == grid[i][j]){
                   return new Pair<Integer, Integer>(i,j);
                }
            }
        }
        return new Pair<Integer, Integer>(-1, -1);
    }
    
    public static char[][] generateGrid(String key){
        char[][] grid = new char[5][5];
        boolean used[] = new boolean[26];
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder keyBuilder = new StringBuilder();

        // Remove duplicates from key

        for( char c: key.toCharArray()){
            if(!used[c - 'A']){ 
                used[c - 'A'] = true;
                keyBuilder.append(c);
            }
        }

        // Filling the with remaining Alphabets into keybuilder

        for(char c='A'; c<= 'Z'; c++){
            if(c == 'J') continue;
            if(!used[c - 'A']){
                used[c - 'A'] = true;
                keyBuilder.append(c);
            }
        }

        // Filling the grid

        String finalKey = keyBuilder.toString();
        int index = 0;
        for(int row=0; row<5; row++){
            for(int col=0; col<5; col++){
                grid[row][col] = finalKey.charAt(index++);
            }
        }
        
        return grid;
    } 
}

class Pair<F,S>{
    private final F element0;
    private final S element1;

    public Pair(F e0, S e1){
        this.element0 = e0;
        this.element1 = e1;
    }

    public static <F,S> Pair<F,S> createPair(F e0, S e1){
        return new Pair<>(e0, e1);
    }

    public F getElement0(){
        return element0;
    }

    public S getElement1(){
        return element1;
    }
} 