//* 1인분 메뉴 선택: 각 사람이 1인분 메뉴(짜장면, 짬뽕 등)를 선택합니다.
//* 여러 명이 나누어 먹을 요리 선택: 여러 사람이 나누어 먹을 요리(탕수육, 깐풍기 등)를 선택합니다.
//* 요리 제거 기능: 88번을 눌러 이미 선택한 요리 메뉴를 제거할 수 있습니다.
//* 금액 계산: 선택된 요리의 총 금액을 사람 수에 맞게 나누고, 남은 금액은 한 사람이 추가로 부담합니다.
//* 영수증 출력: 각 사람이 선택한 메뉴와 금액을 출력합니다.

package moklanMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.NumberFormat;

class Dish {
    private String name;
    private int servingSize;
    private int price;

    public Dish(String name, int servingSize, int price) {
        this.name = name;
        this.servingSize = servingSize;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-30s %10s원", name + " (인원: " + servingSize + "명)", NumberFormat.getInstance().format(price));
    }
}

class Person {
    private String name;
    private Dish mainDish;
    private int mainDishPrice;
    private int finalDishShare;
    private List<String> finalDishNames;

    public Person(String name) {
        this.name = name;
        this.mainDish = null;
        this.mainDishPrice = 0;
        this.finalDishShare = 0;
        this.finalDishNames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Dish getMainDish() {
        return mainDish;
    }

    public void setMainDish(Dish mainDish) {
        this.mainDish = mainDish;
        this.mainDishPrice = mainDish.getPrice();
    }

    public void addFinalDishShare(int amount) {
        this.finalDishShare += amount;
    }

    public void setFinalDishShare(int finalDishShare) {
        this.finalDishShare = finalDishShare;
    }

    public void addFinalDishName(String finalDishName) {
        finalDishNames.add(finalDishName);
    }

    public int getFinalDishShare() {
        return this.finalDishShare;
    }

    public int getTotalPrice() {
        return mainDishPrice + finalDishShare;
    }

    public List<String> getFinalDishNames() {
        return finalDishNames;
    }
}

public class ChineseCuisine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat nf = NumberFormat.getInstance();

        // 1인분 메뉴 설정
        Dish[] singleServingMenu = {
                new Dish("짜장면", 1, 5000),
                new Dish("짬뽕", 1, 6000),
                new Dish("볶음밥", 1, 7000)
        };

        // 요리 메뉴 설정
        Dish[] multipleServingMenu = {
                new Dish("탕수육", 3, 15000),
                new Dish("깐풍기", 3, 18000),
                new Dish("팔보채", 3, 20000),
                new Dish("깐쇼새우", 3, 22000),
                new Dish("양장피", 3, 25000),
                new Dish("유린기", 3, 18000),
                new Dish("고추잡채", 3, 20000)
        };

        // 인원 수 입력
        int numOfPeople;
        while (true) {
            System.out.print("총 인원 수를 입력하세요: ");
            try {
                numOfPeople = Integer.parseInt(scanner.nextLine());
                if (numOfPeople > 0) break;
                System.out.println("인원 수는 0보다 커야 합니다. 다시 입력하세요.");
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력하세요.");
            }
        }

        // 각 인원별로 이름 입력
        Person[] people = new Person[numOfPeople];
        for (int i = 0; i < numOfPeople; i++) {
            System.out.print((i + 1) + "번째 사람의 이름을 입력하세요: ");
            String name = scanner.nextLine();
            people[i] = new Person(name);
        }

        // 각 인원별로 1인분 메뉴 선택
        for (Person person : people) {
            System.out.println("\n선택 가능한 1인분 메뉴:");
            for (int j = 0; j < singleServingMenu.length; j++) {
                System.out.println((j + 1) + ". " + singleServingMenu[j]);
            }

            while (true) {
                System.out.print(person.getName() + "의 1인분 메뉴를 선택하세요 (1-" + singleServingMenu.length + "): ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= singleServingMenu.length) {
                        Dish mainDish = singleServingMenu[choice - 1];
                        person.setMainDish(mainDish);
                        break;
                    } else {
                        System.out.println("유효한 선택지를 입력하세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("유효한 숫자를 입력하세요.");
                }
            }
        }

        // 요리 선택
        List<Dish> chosenFinalDishes = new ArrayList<>();
        while (true) {
            System.out.println("\n선택 가능한 요리 메뉴:");
            for (int j = 0; j < multipleServingMenu.length; j++) {
                System.out.println((j + 1) + ". " + multipleServingMenu[j]);
            }
            System.out.println("88. 선택한 요리 중 제거 메뉴로 진입");
            System.out.println("0. 선택 완료");

            // 현재까지 선택된 요리 출력
            System.out.println("\n현재 선택된 요리:");
            if (chosenFinalDishes.isEmpty()) {
                System.out.println("선택된 요리가 없습니다.");
            } else {
                for (Dish dish : chosenFinalDishes) {
                    System.out.println("- " + dish.getName());
                }
            }

            System.out.print("요리를 선택하세요: ");
            try {
                int finalChoice = Integer.parseInt(scanner.nextLine());
                if (finalChoice == 0) break;

                if (finalChoice == 88) {
                    // 제거 메뉴로 진입
                    if (chosenFinalDishes.isEmpty()) {
                        System.out.println("제거할 요리가 없습니다.");
                        continue;
                    }
                    System.out.println("\n제거 가능한 요리:");
                    for (int j = 0; j < chosenFinalDishes.size(); j++) {
                        System.out.println((j + 1) + ". " + chosenFinalDishes.get(j).getName());
                    }
                    System.out.print("제거할 요리를 선택하세요: ");
                    try {
                        int removeChoice = Integer.parseInt(scanner.nextLine());
                        if (removeChoice >= 1 && removeChoice <= chosenFinalDishes.size()) {
                            Dish removedDish = chosenFinalDishes.remove(removeChoice - 1);
                            System.out.println(removedDish.getName() + " 제거되었습니다.");
                        } else {
                            System.out.println("유효한 선택지를 입력하세요.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("유효한 숫자를 입력하세요.");
                    }
                    continue;
                }

                if (finalChoice >= 1 && finalChoice <= multipleServingMenu.length) {
                    Dish chosenDish = multipleServingMenu[finalChoice - 1];
                    chosenFinalDishes.add(chosenDish);
                    for (Person person : people) {
                        person.addFinalDishName(chosenDish.getName());
                    }
                } else {
                    System.out.println("유효한 선택지를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력하세요.");
            }
        }

        // 요리의 총 금액 계산
        int totalFinalDishPrice = 0;
        for (Dish dish : chosenFinalDishes) {
            totalFinalDishPrice += dish.getPrice();
        }
        int baseFinalDishShare = totalFinalDishPrice / numOfPeople;
        int remainder = totalFinalDishPrice % numOfPeople;

        for (Person person : people) {
            person.setFinalDishShare(baseFinalDishShare);
        }

        // 나머지 금액을 분담할 사람 선택
        if (remainder > 0) {
            System.out.println("\n총 금액이 1/n로 정확히 나누어지지 않습니다. 추가로 더 지불할 사람을 선택해 주세요.");
            for (int i = 0; i < people.length; i++) {
                System.out.println((i + 1) + ". " + people[i].getName());
            }

            while (true) {
                System.out.print("추가로 더 지불할 사람을 선택하세요 (1-" + people.length + "): ");
                try {
                    int extraPayerIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (extraPayerIndex >= 0 && extraPayerIndex < people.length) {
                        people[extraPayerIndex].addFinalDishShare(remainder);
                        break;
                    } else {
                        System.out.println("유효한 선택지를 입력하세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("유효한 숫자를 입력하세요.");
                }
            }
        }

        // 영수증 출력
        System.out.println("\n========== 영수증 ==========");
        System.out.println("선택한 요리:");
        chosenFinalDishes.forEach(dish -> System.out.printf("%-35s %10s원\n", "- " + dish.getName(), nf.format(dish.getPrice())));
        System.out.printf("%-35s %10s원\n", "총 금액:", nf.format(totalFinalDishPrice));
        System.out.println("============================");

        for (Person person : people) {
            System.out.println("이름: " + person.getName());
            System.out.printf("%-30s %10s원\n", "1인분 메뉴: " + person.getMainDish().getName(), nf.format(person.getMainDish().getPrice()));
            System.out.printf("%-30s %10s원\n", "요리 분담 금액 (" + String.join(", ", person.getFinalDishNames()) + "):", nf.format(person.getFinalDishShare()));
            System.out.printf("%-30s %10s원\n", "총 지불 금액:", nf.format(person.getTotalPrice()));
            System.out.println("------------------------------");
        }


        scanner.close();
    }
}
