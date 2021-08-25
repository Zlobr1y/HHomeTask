import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String var1 = reader.readLine();
        String[] checksAndManagers = var1.split(" ");
        int checks = Integer.parseInt(checksAndManagers[0]);
        int managers = Integer.parseInt(checksAndManagers[1]);
        int sum = 0;
        validateInputParams(checks);
        validateInputParams(managers);
        int[] moneyOnChecks = new int[checks];

        for (int i = 0; i < moneyOnChecks.length; i++) {
            moneyOnChecks[i] = Integer.parseInt(reader.readLine());
            validateInputMoneyOnChecks(moneyOnChecks[i]);
            sum += moneyOnChecks[i];
        }
        System.out.println(calculateMaxBonus(sum, managers, moneyOnChecks));
    }

    private static void validateInputParams(int number) {
        if (number <= 0) {
            System.out.println("Число " + number + " меньше или равно нулю.");
        } else if (number >= 100000) {
            System.out.println("Число " + number + " превышает допустимый лимит.");
        }
    }

    private static void validateInputMoneyOnChecks(int number) {
        if (number < 0) {
            System.out.println("На счету не может быть отрицательное кол-во денег.");
        } else if (number >= 100000000) {
            System.out.println("Количество денег на счету превышает допустимый лимит.");
        }
    }

    private static int calculateMaxBonus(int sum, int countManagers, int[] moneyOnChecks) {
        int maxBonus = sum / countManagers;
        int count = 0;
        if (maxBonus < 1) {
            return 0;
        }
        while (count < countManagers) {
            for (int moneyOnCheck : moneyOnChecks) {
                count += moneyOnCheck / maxBonus;
            }
            if (count < countManagers) {
                maxBonus--;
                count = 0;
            }
        }
        return maxBonus;
    }
}
