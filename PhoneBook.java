import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в телефонный справочник");
        System.out.println("Выберите действие: 1 - записать данные, 2 - показать данные, 3 - найти по имени, 4 - закончить работу");
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        while (choise == 1 || choise == 2 || choise == 3) {
            if (choise == 1) {
                add();
                System.out.println("Данные успешно добавлены!");
            }
            else if (choise == 2) {
                System.out.println("Телефонный справочник:");
                System.out.println(getPhoneBook(phoneBook));
            }
            else if (choise == 3) {
                System.out.println("Введите имя:");
                Scanner scr = new Scanner(System.in);
                String imy = scr.nextLine();
                if (find(imy) != null) {
                    System.out.println("Телефонные номера для " + imy + ":");
                    System.out.println(find(imy));
                } else {
                    System.out.println("Телефонных номеров для " + imy + " нет.");
                }
            }
            System.out.println("Выберите действие: 1 - записать данные, 2 - показать данные, 3 - найти по имени, 4 - закончить работу");
            choise = scanner.nextInt();
        }
    }

    public static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите номер телефона:");
        int phoneNum = scanner.nextInt();
        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phoneNum);
        } else {
            phoneBook.put(name, new ArrayList<>(Arrays.asList(phoneNum)));
        }
    }

    public static ArrayList<Integer> find(String name) {
        for (String key : phoneBook.keySet()) {
            if (key.equals(name)) {
                return phoneBook.get(key);
            }
        }
        return new ArrayList<>();
    }

    public static HashMap<String, ArrayList<Integer>> getPhoneBook(HashMap<String, ArrayList<Integer>> phoneBook) {
        List<Map.Entry<String, ArrayList<Integer>>> list = new LinkedList<>(phoneBook.entrySet());
        list.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
    
        HashMap<String, ArrayList<Integer>> sortedPhoneBook = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : list) {
            sortedPhoneBook.put(entry.getKey(), entry.getValue());
        }
        return sortedPhoneBook;
    }
}