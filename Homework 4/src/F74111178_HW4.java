import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class F74111178_HW4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigDecimal[][] coefficients = new BigDecimal[n][n+1];
        boolean flag = true ;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n + 1 ; j++) {
                BigDecimal temp = scanner.nextBigDecimal();
                if(temp.compareTo(BigDecimal.valueOf(-999)) == 0){
                    for(int p=i ; p<n ; p++){
                        for(int q=0 ; q<n+1 ; q++){
                            coefficients[p][q] = BigDecimal.ZERO;
                        }
                    }
                    flag = false;
                    break;
                }
                coefficients[i][j] = temp;
            }
            if(flag == false){
                break;
            }
        }
        if(flag == true){
            scanner.nextBigDecimal();
        }
        for(int i=0 ; i<n ; i++){
            if(coefficients[i][0].compareTo(BigDecimal.ZERO)<0){
                for(int j=0 ; j<n+1 ; j++){
                    coefficients[i][j] = coefficients[i][j].multiply(BigDecimal.valueOf(-1));
                }
            }
        }
        Arrays.sort(coefficients, Comparator.comparing(row -> row[0], Comparator.reverseOrder()));
        System.out.println(doGaussianElimination(coefficients));
    }
    public static String doGaussianElimination(BigDecimal[][] matrix) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        for (int row = 0, col = 0 ; row < rowCount ; row++, col++) {
            if(row == rowCount-1 || col == columnCount-1){
                return determineOutput(matrix, rowCount, columnCount);
            }
            while(matrix[row][col].compareTo(BigDecimal.ZERO)==0){
                col++;
                if(col>columnCount-1){
                    break;
                }
                sortMatrix(matrix,rowCount,columnCount,row-1,col-1);
            }
            if(col>=columnCount-1){
                return determineOutput(matrix, rowCount, columnCount);
            }
            for (int i = row + 1; i < rowCount; i++) {
                if(matrix[i][col].compareTo(matrix[row][col])==0){
                    for (int j = col; j < columnCount; j++) {
                        matrix[i][j] = matrix[i][j].subtract(matrix[row][j]);
                    }
                }
                else if (matrix[i][col].compareTo(BigDecimal.ZERO)!=0){
                    BigDecimal temp_i = matrix[i][col];
                    BigDecimal temp_row = matrix[row][col];
                    for (int j = col; j < columnCount; j++) {
                        matrix[row][j] = matrix[row][j].multiply(temp_i);
                        matrix[i][j] = matrix[i][j].multiply(temp_row);
                        matrix[i][j] = matrix[i][j].subtract(matrix[row][j]);
                    }
                }
            }
            sortMatrix(matrix,rowCount,columnCount,row,col);
        }
        return "The only solution";
    }
    public static void sortMatrix(BigDecimal[][] matrix, int rowCount, int columnCount, int row, int col){
        BigDecimal[][] temp_matrix = new BigDecimal[rowCount-row-1][columnCount - col-1];
        for(int i=0 ; i<rowCount-row-1 ; i++){
            for(int j=0 ; j<columnCount-col-1 ; j++){
                BigDecimal temp = matrix[row+1+i][col+1+j];
                temp_matrix[i][j] = temp;
            }
        }
        for(int i=0 ; i<rowCount-row-1 ; i++){
            if(temp_matrix[i][0].compareTo(BigDecimal.ZERO)<0){
                for(int j=0 ; j<columnCount-col-1 ; j++){
                    temp_matrix[i][j] = temp_matrix[i][j].multiply(BigDecimal.valueOf(-1));
                }
            }
        }
        Arrays.sort(temp_matrix, Comparator.comparing(temp_row -> temp_row[0], Comparator.reverseOrder()));
        for(int i=0 ; i<rowCount-row-1 ; i++){
            for(int j=0 ; j<columnCount-col-1 ; j++){
                BigDecimal temp = temp_matrix[i][j];
                matrix[row+1+i][col+1+j] = temp;
            }
        }
    }
    public static String determineOutput(BigDecimal[][] matrix, int rowCount, int columnCount){
        for(int i=0 ; i<rowCount ; i++){
            boolean flag = false;
            for(int j=0 ; j<columnCount-1 ; j++){
                if(matrix[i][j].compareTo(BigDecimal.ZERO) !=0 ) {
                    flag = true;
                    break;
                }
            }
            if(flag==false && matrix[i][columnCount-1].compareTo(BigDecimal.ZERO)==0){
                return "Infinite solutions";
            }
            else if (flag==false && matrix[i][columnCount-1].compareTo(BigDecimal.ZERO)!=0){
                return "No solution";
            }
        }
        return "The only solution";
    }
}