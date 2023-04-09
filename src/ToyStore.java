import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ToyStore {
    private ArrayList<Toy> toyList;
    private ArrayList<Toy> prizeList;

    public ToyStore() {
        this.toyList = new ArrayList<Toy>();
        this.prizeList = new ArrayList<Toy>();
    }

    public void addToy(Toy toy) {
        this.toyList.add(toy);
    }
    public void addNewToy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id?:");
        int newId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("название?:");
        String newName = scanner.nextLine();
        System.out.println("колличество?:");
        int newQuantity = scanner.nextInt();
        System.out.println("вероятность выпадения?:");
        int newWeight = scanner.nextInt();

        Toy newToy = new Toy(newId, newName, newQuantity, newWeight);
        this.addToy(newToy);

        System.out.println("новая игрушка успешно добавлена");
    }

    public void updateToyWeight(int id, int weight) {
        for (Toy toy : this.toyList) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void initPrizeList() {
        Random rand = new Random();

        for (Toy toy : this.toyList) {
            int weight = toy.getWeight();
            int count = (int) Math.round((double) weight / 100 * 1000);

            for (int i = 0; i < count; i++) {
                this.prizeList.add(toy);
            }
        }

        Collections.shuffle(this.prizeList);
    }

    public String getPrize() {
        Toy prize = this.prizeList.get(0);
        this.prizeList.remove(0);

        int quantity = prize.getQuantity();
        prize.setQuantity(quantity - 1);

        return prize.getName();
    }

    public void modifyToy(int id) {
        Toy toy = null;
        for (Toy t : this.toyList) {
            if (t.getId() == id) {
                toy = t;
                break;
            }
        }

        if (toy == null) {
            System.out.println(" ничего подобного");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("назваие? :");
        String newName = scanner.nextLine();
        System.out.println("вероятность выпадения? :");
        int newWeight = scanner.nextInt();
        System.out.println("колличество?:");
        int newQuantity = scanner.nextInt();


        toy.setWeight(newWeight);
        toy.setQuantity(newQuantity);

        System.out.println("Изменения пиняты");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Розыгрыш игрушек");
        while (true) {
            System.out.println("введи  '1' для начала розыгрыша,\n " +
                    "'2' для изменения данных об игрушках,\n '3' добавить новую ирушку в магазин" +
                    "\n '0' - выход");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            if (choice == 2) {
                System.out.println("Список игрушек");
                for (Toy toy : this.toyList) {
                    System.out.println(toy.getId() + "- " + toy.getName());
                }
                System.out.println("введи номер игрушки для изменения");
                int id = scanner.nextInt();
                modifyToy(id);
                continue;
            }
            if (choice == 3) {
                addNewToy();
                continue;
            }
            if (choice == 1) {
                this.initPrizeList();

                System.out.println("Что есть:");
                for (Toy toy : this.toyList) {
                    System.out.println(toy.getId() + "- " + toy.getName() + " (" + toy.getQuantity() + ")" + " вероятность выйгрыша- " + toy.getWeight());
                }

                System.out.println(" '1' продолжаeм, '0' - выход:");
                choice = scanner.nextInt();

                if (choice == 0) {
                    continue;
                }

                while (!this.prizeList.isEmpty()) {
                    String prize = this.getPrize();
                    this.writeToFile("prizes.txt", prize);
                    System.out.println(String.format("Поздравляем! вы выйграли : %s", prize));

                    System.out.println("введите '1' для следующего розыгрыша, '0' для выхода из программы:");
                    choice = scanner.nextInt();

                    if (choice == 0) {
                        break;
                    }
                }

                System.out.println("кончилось всё");
            }
            else {
                System.out.println("ещё раз");
            }
        }
    }

    public void writeToFile(String fileName, String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(String.format("%s\n", text));
            writer.close();
        } catch (IOException e) {
            System.out.println("Не получилось");
        }
    }

    public static void main(String[] args) {
        ToyStore store = new ToyStore();

        store.addToy(new Toy(1, "мишка", 5, 30));
        store.addToy(new Toy(2, "кролик", 10, 20));
        store.addToy(new Toy(3, "машинка", 8, 10));
        store.addToy(new Toy(4, "акула", 12, 25));
        store.addToy(new Toy(5, "кукла", 7, 5));
        store.addToy(new Toy(6, "котик", 6, 10));



        store.run();
    }
}