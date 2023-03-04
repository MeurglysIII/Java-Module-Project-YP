import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private int numberOfPeople;
    private ArrayList<Item> items = new ArrayList<>();
    private double sum;

    public Calculator (int numberOfPeople){
        if (numberOfPeople > 1) {
            this.numberOfPeople = numberOfPeople;
            sum = 0.;
        } else {
            throw new IllegalArgumentException("Неверное число человек: " + numberOfPeople);
        }
    }

    private class Item {
        private String name;
        private double price;

        public Item (String name, double price){
            this.name = name;
            if (price >= 0.0){
                this.price = price;
            } else {
                throw new IllegalArgumentException("Неверная цена: " + price);
            }
        }

        public void showItem(){
            System.out.println(name + " -> " + Price.priceRub(price));
        }
    }

    private static class Price {
        public static String priceRub(double price){
            String number;
            String currency;

            if (price < 0) {
                throw new IllegalArgumentException("Неверная цена: " + price);
            }

            number = String.format("%.2f", price);

            int priceInt = (int) price;

            if (priceInt % 10 == 0 || (priceInt % 100 >= 11 && priceInt % 100 <= 19) ||
                    (priceInt % 10 >= 5 && priceInt % 10 <= 9)) {
                currency = "рублей";
            } else if (priceInt % 10 == 1) {
                currency = "рубль";
            } else if (priceInt % 10 >= 2 && priceInt % 10 <= 4) {
                currency = "рубля";
            } else {
                currency = "???";
            }

            return number + " " + currency;
        }
    }

    private void addItem (String name, double price){
        items.add(new Item(name, price));
        sum += price;
    }

    public void getItemsFromUser() {
        String endCode = "Завершить";

        System.out.println("Вам будет предложено ввести список товаров по одному. \nДля завершения добавления"
                         + " товаров введите команду \"" + endCode + "\".");
        String itemName;
        double itemPrice;
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //Считываем название товара
            System.out.println("Введите название товара.");
            itemName = scanner.nextLine().trim();
            if (itemName.isEmpty()) {
                System.out.println("Введена пустая строка.");
                continue;
            }

            //Считываем цену товара
            while (true) {
                System.out.println("Введите стоимость товара в рублях.");
                input = scanner.nextLine().trim();
                try {
                    itemPrice = Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Формат числа не подходит.");
                    continue;
                }

                if (itemPrice < 0) {
                    System.out.println("Цена должна быть >= 0.");
                } else {
                    break;
                }
            }

            this.addItem(itemName, itemPrice);

            System.out.println("Вы успешно добавили товар " + itemName + ".");

            System.out.println("Хотите ли вы добавить ещё один товар? Введите \"" + endCode + "\", чтобы завершить"
                             + " добавление товаров");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(endCode)) {
                break;
            }
        }
    }

    public void showItems() {
        if (items.isEmpty()){
            System.out.println("Нет товаров в списке.");
            return;
        }

        System.out.println("\nДобавленные товары:\n");
        for (Item item: items){
            item.showItem();
        }

        System.out.println("\nЦена на человека -> " + Price.priceRub(sum / numberOfPeople));
    }
}
