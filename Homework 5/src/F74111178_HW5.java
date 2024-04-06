import java.util.Arrays;
import java.util.Scanner;

public class F74111178_HW5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        char[][] input = new char[n][m];
        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<m ; j++){
                input[i][j] = scanner.next().charAt(0);
            }
        }
        String target = scanner.next();

        int i=0, j=0, x=0;
        boolean hasLetter = false;

        char[][] preservedArray = copyArray(input);
        for(i=0 ; i<n ; i++){
            for(j=0 ; j<m ; j++){
                if(input[i][j] == target.charAt(x)){
                    input[i][j] = '@';
                    hasLetter = true;

                    String found = findTheWord(input, i, j, target, (x+1));

                    if(found.equals("false")){
                        input = copyArray(preservedArray);
                        hasLetter = false;
                    }
                    else if(found.equals("true")){
                        System.out.println("true");
                        break;
                    }
                }
            }
            if(hasLetter == true){
                break;
            }
        }
        if(hasLetter == false){
            System.out.println("false");
        }
    }
    public static String findTheWord(char[][] input, int i, int j, String target, int x){
        if(x==target.length()){
            return "true";
        }
        for(int round=0 ; round<4 ; round++) {
            if (i - 1 >= 0 && round == 0) {
                if (input[i - 1][j] == target.charAt(x) && input[i - 1][j] != '@') {
                    int temp = i - 1;
                    input[temp][j] = '@';
                    if (findTheWord(input, temp, j, target, (x + 1)).equals("true")) {
                        return "true";
                    }
                    else {
                        input[temp][j]=target.charAt(x);
                    }
                }
            }
            if (j - 1 >= 0 && round == 1) {
                if (input[i][j - 1] == target.charAt(x) && input[i][j - 1] != '@') {
                    int temp = j - 1;
                    input[i][temp] = '@';
                    if (findTheWord(input, i, temp, target, (x + 1)).equals("true")) {
                        return "true";
                    }
                    else {
                        input[i][temp]=target.charAt(x);
                    }
                }
            }
            if (i + 1 < input.length && round == 2) {
                if (input[i + 1][j] == target.charAt(x) && input[i + 1][j] != '@') {
                    int temp = i + 1;
                    input[temp][j] = '@';
                    if (findTheWord(input, temp, j, target, (x + 1)).equals("true")) {
                        return "true";
                    }
                    else {
                        input[temp][j]=target.charAt(x);
                    }
                }
            }
            if (j + 1 < input[0].length && round == 3) {
                if (input[i][j + 1] == target.charAt(x) && input[i][j + 1] != '@') {
                    int temp = j + 1;
                    input[i][temp] = '@';
                    if (findTheWord(input, i, temp, target, (x + 1)).equals("true")) {
                        return "true";
                    }
                    else {
                        input[i][temp]=target.charAt(x);
                    }
                }
            }
        }
        return "false";
    }
    public static char[][] copyArray(char[][] original) {
        int rows = original.length;

        char[][] copied = new char[rows][];
        for (int i = 0; i < rows; i++) {
            copied[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copied;
    }
}
