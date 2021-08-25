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
        int bonus;
        if (checks <= 0 || checks >= 100000) checks = 0;
        if (managers <= 0 || managers >= 100000) managers = 0;
        System.out.println("Кол-во счетов: " + checks + " Кол-во менеджеров: " + managers);

        if (checks > 0 && managers > 0) {

            int[] moneyOnChecks = new int[checks];
            double sum = 0.0;
            for (int i = 0; i < moneyOnChecks.length; i++) {
                moneyOnChecks[i] = Integer.parseInt(reader.readLine());
                if (moneyOnChecks[i] > 100000000){
                    moneyOnChecks[i] = 100000000;
                }
                sum += moneyOnChecks[i];
            }

            bonus = (int) sum / managers;


            System.out.println("Сумма на счетах: " + sum);
            System.out.println("Премия каждого менеджера составила: " + calculateMaxBonus(bonus, managers, moneyOnChecks));

        } else {
            System.out.println("Счетов не существует.");
        }
    }

    private static int calculateMaxBonus(int maxBonus, int countManagers, int[] moneyOnChecks) {
        int count = 0;
        if (maxBonus > 0){
            while (true){
                if (count < countManagers){
                    for (int i = 0; i < moneyOnChecks.length; i++){
                        count += moneyOnChecks[i]/maxBonus;
                    }
                    if (count < countManagers){
                        maxBonus--;
                        count = 0;
                    }
                } else {
                    break;
                }
            }
        } else return 0;
        return maxBonus;
    }
}
