import java.math.BigDecimal;
import java.util.Scanner;

public class F74111178_HW1 {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextBigDecimal()) {
        BigDecimal num1 = scanner.nextBigDecimal();
        BigDecimal num2 = scanner.nextBigDecimal();

        System.out.println(num1.add(num2));
        }
    }
}
