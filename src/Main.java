import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int SUM = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String var1 = reader.readLine();
        String[] checksAndManagers = var1.split(" ");
        int checks = Integer.parseInt(checksAndManagers[0]);
        int managers = Integer.parseInt(checksAndManagers[1]);
        validateInputParams(checks);
        validateInputParams(managers);
        int[] moneyOnChecks = new int[checks];

        for (int i = 0; i < moneyOnChecks.length; i++) {
            moneyOnChecks[i] = Integer.parseInt(reader.readLine());
            validateInputMoneyOnChecks(moneyOnChecks[i]);
            SUM += moneyOnChecks[i];
        }
        sorting(moneyOnChecks, 0, moneyOnChecks.length - 1);
        moneyOnChecks = arrayCutter(checks, managers, moneyOnChecks);
        System.out.println(calculateMaxBonus(SUM, managers, moneyOnChecks));
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

        while (count <= countManagers) {
            for (int moneyOnCheck : moneyOnChecks) {
                count += moneyOnCheck / maxBonus;
            }
            if (count == countManagers){
                return maxBonus;
            } else if (count < countManagers) {
                maxBonus = maxBonus / 2;
                count = 0;
            } else {
                maxBonus = (int) (maxBonus * 1.5);
                count = 0;
            }
        }
        return maxBonus;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = right; j >= left; j--) {
            if (arr[j] > pivot) {
                i = i + 1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;

    }

    private static void sorting(int[] arr, int left, int right) {
        if (left < right) {
            int q = partition(arr, left, right);
            sorting(arr, left, q - 1);
            sorting(arr, q + 1, right);
        }
    }

    private static int[] arrayCutter(int checks, int managers, int[] array) {
        if (checks > managers) {
            SUM = 0;
            int[] arrayCut = new int[managers];
            for (int i = 0; i < arrayCut.length; i++) {
                arrayCut[i] = array[i];
                SUM += arrayCut[i];
            }
            return arrayCut;
        }
        return array;
    }
}
