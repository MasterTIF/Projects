import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.run();
    }
}

enum variants {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCINO(200, 100, 12, 6);

    int water, milk, coffee, price;

    variants(int water, int milk, int coffee, int price) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.price = price;
    }
}

class CoffeeMachine {

    private static int water = 400;
    private static int milk = 540;
    private static int coffee = 120;
    private static int cups = 9;
    private static int money = 550;
    private static boolean running = true;

    void run() {
        // check for input
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String command = input.next();
            switch (command) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappucino, back - to main menu:");
                    buyCoffee(input.next());
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    printDetails();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command, please try again.");
            }
        }
    }

    void fillMachine() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nWrite how many ml of water do you want to add:");
        water += input.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += input.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffee += input.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += input.nextInt();
    }

    void takeMoney() {
        System.out.println("\nI gave you $" + money);
        money = 0;
    }

    void printDetails() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    void buyCoffee(String order) {
        String deficitSupply;
        switch (order) {
            case "back":
                break;
            case "1":
                deficitSupply = getDeficitSupply(variants.ESPRESSO);
                 if (deficitSupply.equals("none")) {
                    deductResources(variants.ESPRESSO);
                } else {
                    alert(deficitSupply);
                }
                break;
            case "2":
                deficitSupply = getDeficitSupply(variants.LATTE);
                if (deficitSupply.equals("none")) {
                    deductResources(variants.LATTE);
                } else {
                    alert(deficitSupply);
                }
                break;
            case "3":
                deficitSupply = getDeficitSupply(variants.CAPPUCINO);
                if (deficitSupply.equals("none")) {
                    deductResources(variants.CAPPUCINO);
                } else {
                    alert(deficitSupply);
                }
                break;

            default:
        }
    }

    void deductResources(variants variant) {
        water -= variant.water;
        milk -= variant.milk;
        coffee -= variant.coffee;
        money += variant.price;
        cups--;
        System.out.println("I have enough resources, making you a coffee!");
    }

    String getDeficitSupply(variants variant) {
        String deficitSupply = "none";
        if (variant.water > water) {
            deficitSupply = "water";
        } else if (variant.milk > milk) {
            deficitSupply = "milk";
        } else if (variant.coffee > coffee) {
            deficitSupply = "coffee";
        } else if (cups < 1) {
            deficitSupply = "cups";
        }
        return deficitSupply;
    }

    void alert(String supply) {
        switch (supply) {
            case "water":
                System.out.println("Sorry, not enough water!");
                break;
            case "milk":
                System.out.println("Sorry, not enough milk!");
                break;
            case "coffee":
                System.out.println("Sorry, not enough coffee!");
                break;
            case "cups":
                System.out.println("Sorry, not enough cups!");
                break;
            default:
        }
    }
}