import java.util.Scanner;

public class CalcIncomeTax {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your income...");
        long salary = scan.nextLong();
        double income_tax = calcIncomeTax(salary);
        System.out.println(income_tax);
    }

    private static double calcIncomeTax(long salary) {
        double total_tax = 0;
        if (salary <= 50000) {
            total_tax = 0;
        } else if (salary <= 60000) {
            total_tax =(salary - 50000) * 0.1;
        } else if (salary <= 150000) {
            total_tax = ((salary -60000) * 0.2 + 1000);
        }else {
            total_tax = ((salary - 150000) * 0.3 + 19000);
        }
        return total_tax;
    }
}
