import java.util.Scanner;

// dev branch for Y.Practicum
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Привет! На сколько человек нужно разделить счёт?");

        int numberOfPeople;
        String input;
        while (true){
            input = scanner.nextLine().trim();

            try {
                numberOfPeople = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ввод неверный. Пожалуста, введите число человек.");
                continue;
            }

            if (numberOfPeople <= 1) {
                System.out.println("Число человек должно быть больше 1, чтобы имело смысл разделение счёта."
                                 + "\nВведите верное число.");
            } else {
                break;
            }
        }

        Calculator calculator;

        try {
            calculator = new Calculator(numberOfPeople);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            scanner.close();
            return;
        }

        calculator.getItemsFromUser();
        calculator.showItems();

        scanner.close();
    }
}
