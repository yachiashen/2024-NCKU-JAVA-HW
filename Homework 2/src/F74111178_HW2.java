import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class F74111178_HW2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i=0 ; i<n ; i++) {
            String num1 = scanner.next();
            String num2 = scanner.next();

            BigInteger decimal_1 = countDecimalPlaces(num1);
            BigInteger decimal_2 = countDecimalPlaces(num2);

            String[] number1_parts = num1.split("\\.");
            String[] number2_parts = num2.split("\\.");
            BigInteger temp_int1 = new BigInteger(number1_parts[0]);
            BigInteger temp_int2 = new BigInteger(number2_parts[0]);
            BigInteger temp_dec1 = BigInteger.ZERO;
            BigInteger temp_dec2 = BigInteger.ZERO;
            if(decimal_1.compareTo(BigInteger.ZERO)>0) {
                temp_dec1 = new BigInteger(number1_parts[1]);
            }
            if(decimal_2.compareTo(BigInteger.ZERO)>0) {
                temp_dec2 = new BigInteger(number2_parts[1]);
            }
            BigInteger temp_int_add = temp_int1.add(temp_int2);
            String integer = temp_int_add.toString();

            BigInteger decimal_lcm;
            if(decimal_1.equals(BigInteger.ZERO) && decimal_2.equals(BigInteger.ZERO)){
                BigDecimal x = new BigDecimal(num1);
                BigDecimal y = new BigDecimal(num2);
                System.out.println(x.add(y));
            }else if (decimal_1.equals(BigInteger.ZERO) || decimal_2.equals(BigInteger.ZERO)){
                BigDecimal x = new BigDecimal(num1);
                BigDecimal y = new BigDecimal(num2);

                BigDecimal ans = x.add(y);
                if(decimal_1.equals(BigInteger.ZERO)){
                    BigInteger denominator = resolveWithDenominator(decimal_2);
                    if( temp_dec2.compareTo(denominator) >= 0 ){
                        String temp_str = denominator.toString();
                        temp_str = "0."+temp_str;
                        BigDecimal temp_dec = new BigDecimal(temp_str);
                        ans = (ans.add(BigDecimal.ONE).subtract(temp_dec));
                    }
                }else{
                    BigInteger denominator = resolveWithDenominator(decimal_1);
                    if( temp_dec1.compareTo(denominator) >= 0 ){
                        String temp_str = denominator.toString();
                        temp_str = "0."+temp_str;
                        BigDecimal temp_dec = new BigDecimal(temp_str);
                        ans = (ans.add(BigDecimal.ONE).subtract(temp_dec));
                    }
                }
                if(ans.scale()==1) {
                    ans = ans.stripTrailingZeros();
                }
                System.out.println(ans);
            } else {
                decimal_lcm = (decimal_1.multiply(decimal_2)).divide(decimal_1.gcd(decimal_2));
                System.out.println(addTheNumber(number1_parts, number2_parts, integer, decimal_lcm));
            }
        }
    }
    public static BigInteger countDecimalPlaces(String str){
        int dot = str.indexOf('.');
        if (dot==-1){
            return BigInteger.ZERO;
        }

        String[] parts = str.split("\\.");
        BigInteger temp = new BigInteger(parts[1]);
        if (parts[1].isEmpty() || temp.equals(BigInteger.ZERO)) {
            return BigInteger.valueOf(0);
        }
        return BigInteger.valueOf(parts[1].length());
    }
    public static String addTheNumber(String[] number1_parts, String[] number2_parts, String integer, BigInteger lcm){
        BigInteger denominator = resolveWithDenominator(lcm);

        BigInteger times;
        String temp = "";
        times = lcm.divide(BigInteger.valueOf(number1_parts[1].length()));
        temp = number1_parts[1];
        for (BigInteger i=BigInteger.ZERO ; i.compareTo(times.subtract(BigInteger.ONE)) < 0 ; i=i.add(BigInteger.ONE)){
            temp += number1_parts[1];
        }
        BigInteger temp1 = new BigInteger(temp);

        times = lcm.divide(BigInteger.valueOf(number2_parts[1].length()));
        temp = number2_parts[1];
        for (BigInteger i=BigInteger.ZERO ; i.compareTo(times.subtract(BigInteger.ONE)) < 0 ; i=i.add(BigInteger.ONE)){
            temp += number2_parts[1];
        }
        BigInteger temp2 = new BigInteger(temp);

        BigInteger temp_add = temp1.add(temp2);

        int flag = 0;
        while( temp_add.compareTo(denominator) >= 0 ){
            temp_add = temp_add.subtract(denominator);
            flag ++;
        }

        String decimal;
        if (temp_add.equals(BigInteger.ZERO)){
            decimal = "";
        }else{
            decimal = temp_add.toString();
            while ((BigInteger.valueOf(decimal.length())).compareTo(lcm)<0){
                decimal = "0"+decimal;
            }
            if ((BigInteger.valueOf(decimal.length())).compareTo(lcm)==0){
                if (allCharactersEqual(decimal)){
                    decimal = decimal.substring(0,1);
                }
                decimal = "."+decimal;
            }
        }
        String output = integer+decimal;

        while(flag > 0){
            BigDecimal output_temp = new BigDecimal(output);
            output_temp = output_temp.add(BigDecimal.ONE);
            output = String.valueOf(output_temp);
            flag--;
        }
        return output;
    }
    public static boolean allCharactersEqual(String str) {
        char firstChar = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger resolveWithDenominator(BigInteger n){
        String temp_denominator = "";
        for(BigInteger i=BigInteger.ZERO ; i.compareTo(n) < 0 ; i=i.add(BigInteger.ONE)){
            temp_denominator += "9";
        }
        BigInteger output = new BigInteger(temp_denominator);
        return output;
    }
}