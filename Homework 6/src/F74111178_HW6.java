import java.util.Scanner;

public class F74111178_HW6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        boolean temp = inspectInput(str);
        if(temp == false){
            System.out.println("invalid");
        }
        else if(temp == true){
            System.out.println("valid");
        }
    }
    public static Boolean inspectInput(String str){
        if(str.length()>9){
            return false;
        }
        char[] input = str.toCharArray();
//        for(int i=0 ; i<input.length ; i++){
//            System.out.print(input[i]);
//            if((i+1)%3==0){
//                System.out.println();
//            }
//            else{
//                System.out.print(" ");
//            }
//        }
        int X=0, O=0, blank=0;
        String index_X="";
        String index_O="";
        for(int i=0, j=0 ; i<9 ; i++){
            if(input[i]=='X') {
                index_X = index_X.concat(String.valueOf(i));
                X++;
            }
            else if(input[i]=='O') {
                index_O = index_O.concat(String.valueOf(i));
                O++;
            }
            else if(input[i]=='#') {
                blank++;
            }
        }
        if(X<O || X-O>1){
            return false;
        }
        else if(X==O){
            if(hasLine(index_X) == true){
                return false;
            }
        }
        else if(X>O){
            if(hasLine(index_X) && hasLine(index_O)){
                return false;
            }
            else if(hasLine(index_O)){
                return false;
            }
        }
        return true;
    }
    public static boolean hasLine(String index){
        if ( (index.contains("0")&&index.contains("1")&&index.contains("2")) ||                   //  0 1 2  //
                (index.contains("3")&&index.contains("4")&&index.contains("5")) ||                //  3 4 5  //
                (index.contains("6")&&index.contains("7")&&index.contains("8")) ||                //  6 7 8  //
                (index.contains("0")&&index.contains("3")&&index.contains("6")) ||
                (index.contains("1")&&index.contains("4")&&index.contains("7")) ||
                (index.contains("2")&&index.contains("5")&&index.contains("8")) ||
                (index.contains("0")&&index.contains("4")&&index.contains("8")) ||
                (index.contains("2")&&index.contains("4")&&index.contains("6")) ) {
            return true;
        }
        return false;
    }
}
