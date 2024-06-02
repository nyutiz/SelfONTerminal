package nyutiz;

import java.util.Arrays;
import java.util.Scanner;

import static nyutiz.Database.initializeDatabase;

public class Main {

    private static int maxPrintLength = 70;
    private static String lastPrint;

    private static Scanner scanner = new Scanner(System.in);
    private static Database database;

    private static String baseFile;


    public static void main(String[] args) throws Exception {
        //System.out.println("╔══════════════════════════════════════════════════════════╗");

        try {
            if (args[0] != null){
                database = new Database();
                database.loadDatabase(args[0]);
                baseFile = args[0];
            }
        } catch (Exception e) {

        }

        printWith(" Welcome to SelfON ", "╔", "╗");
        println("0 : Load Database");
        println("1 : Password Manager");
        println("2 : Task Manager");
        println("3 : Database Manager");

        println("╝");

        start();
    }

    public static void start() throws Exception {
        System.out.print("╠═ ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        lastPrint = " ";
        switch (choice){
            case 0:
                //if (database != null){
                //    println("A Database is already loaded");
                //    start();
                //    break;
                //}
                print("Please provide the Database path : ");
                database = new Database();
                baseFile = scanner.nextLine();
                database.loadDatabase(baseFile);
                start();
                break;
            case 1:
                if (database == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                    start();
                    break;
                }
                break;

            case 2:
                //a
                if (database == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                    start();
                    break;
                }
                break;

            case 3:
                if (database == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                    println("0 : Load Database");
                    println("1 : Create Database");
                    println("2 : Return to the menu");
                    System.out.print("╠══ ");
                    lastPrint = " ";
                    int select;
                    select = scanner.nextInt();
                    scanner.nextLine();

                    if(select == 0){
                        print("Please provide the Database path : ");
                        database = new Database();
                        baseFile = scanner.nextLine();
                        database.loadDatabase(baseFile);
                        start();
                        break;
                    }
                    else if (select == 1){
                        print("Enter database name: ");
                        String dbName = scanner.nextLine();
                        lastPrint = " ";
                        print("Enter data (space-separated): ");
                        String[] datas = scanner.nextLine().replaceAll(" ", ";").split(";");
                        lastPrint = " ";
                        println("Database Name: " + dbName);
                        println("Data: " + Arrays.toString(datas));

                        initializeDatabase(dbName, datas);

                        start();
                        break;
                    }
                    else if(select == 2){
                        start();
                        break;
                    }
                    else {
                        start();
                        break;
                    }
                }
                println("╗");
                println("1 : Create Account");
                println("2 : Edit Account");
                println("3 : Show Accounts");
                println("4 : Sort Database");
                println("5 : Create Database");
                System.out.print("╠══ ");
                lastPrint = " ";
                int select;
                String id;
                select = scanner.nextInt();
                scanner.nextLine();
                switch (select) {
                    case 1:
                        println("╣");
                        id = database.generateRandomId();
                        println("Id : " + id);
                        Data newAccount = new Data();
                        newAccount.setData("Id", id);

                        for (String descriptor : database.getDescriptors()) {
                            if (!descriptor.equals("Id")) {
                                print(descriptor + " : ");
                                String value = scanner.nextLine();
                                newAccount.setData(descriptor, value);
                            }
                        }
                        database.addData(newAccount);
                        database.displayData();
                        start();
                        break;
                    case 2:
                        println("╣");
                        print("Id : ");
                        id = scanner.nextLine();
                        Data client = database.findClientById(id);
                        println(client.getDatas().toString());
                        println("Modifier ");
                        for (int i = 1; i < database.getDescriptors().size(); i++) {
                            println((i) + " " + database.getDescriptors().get(i));
                        }
                        System.out.print("╠══ ");
                        lastPrint = " ";
                        int modifyChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (modifyChoice > 0 && modifyChoice < database.getDescriptors().size()) {
                            String fieldToModify = database.getDescriptors().get(modifyChoice);
                            print("Nouveau " + fieldToModify + " : ");
                            String newValue = scanner.nextLine();
                            database.updateData(id, fieldToModify, newValue);
                        } else {
                            println("Choix invalide");
                        }
                        start();
                        break;
                    case 3:
                        printWith(" Number of Datas : " + database.dataList.size() + " ");
                        database.displayData();
                        start();
                        break;
                    case 4:
                        println("Choisissez un descripteur pour trier les comptes :");
                        for (int i = 0; i < database.getDescriptors().size(); i++) {
                            println((i + 1) + ": " + database.getDescriptors().get(i));
                        }
                        System.out.print("╠══ ");
                        lastPrint = " ";
                        int sortChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (sortChoice > 0 && sortChoice <= database.getDescriptors().size()) {
                            String sortDescriptor = database.getDescriptors().get(sortChoice - 1);
                            database.sortData(sortDescriptor);
                            database.displayData();
                            database.saveDatabase(baseFile);
                        } else {
                            println("Choix invalide");
                        }
                        start();
                        break;
                    case 5:
                        print("Enter database name: ");
                        String dbName = scanner.nextLine();
                        lastPrint = " ";
                        print("Enter data (space-separated): ");
                        String[] datas = scanner.nextLine().replaceAll(" ", ";").split(";");
                        lastPrint = " ";
                        println("Database Name: " + dbName);
                        println("Data: " + Arrays.toString(datas));

                        initializeDatabase(dbName, datas);

                        start();
                        break;
                    default:
                        start();
                }
                break;

            default:

                break;
        }
    }

    public static void printWith(String text, String start, String end){
        int k = maxPrintLength - start.length() - end.length();
        int textLength = text.length();
        int padding = (k - textLength) / 2;
        String finalText = start + "═".repeat(padding) + text + "═".repeat(k - textLength - padding) + end;
        System.out.println(finalText);
        lastPrint = finalText;
    }

    public static void printWith(String text){
        int k = maxPrintLength - 2;
        int textLength = text.length();
        int padding = (k - textLength) / 2;
        String finalText = "╠" + "═".repeat(padding) + text + "═".repeat(k - textLength - padding);
        if (lastPrint.endsWith("╣") || lastPrint.endsWith("╗") || lastPrint.endsWith("║")){
            finalText = finalText + "╣";
        }
        else {
            finalText = finalText + "╗";
        }
        System.out.println(finalText);
        lastPrint = finalText;
    }

    public static void printWith(String text, String space) {
        int k = maxPrintLength - 2;
        int textLength = text.length();
        int spaceLength = space.length();
        int padding = (k - textLength) / (2 * spaceLength);
        int remainingPadding = (k - textLength) % (2 * spaceLength);

        StringBuilder finalText = new StringBuilder();
        finalText.append("╠");
        for (int i = 0; i < padding; i++) {
            finalText.append(space);
        }
        finalText.append(text);
        for (int i = 0; i < padding; i++) {
            finalText.append(space);
        }
        if (remainingPadding > 0) {
            finalText.append(space, 0, remainingPadding);
        }
        if (lastPrint.endsWith("╣") || lastPrint.endsWith("╗") || lastPrint.endsWith("║")) {
            finalText.append("╣");
        } else {
            finalText.append("╗");
        }
        System.out.println(finalText);
        lastPrint = finalText.toString();
    }

    public static void printLarge(String text){
        if (text.length() < maxPrintLength) {
            println(text);
        } else {
            int k = maxPrintLength - 4;
            int j = 0;

            while (j < text.length()) {
                int end = Math.min(j + k, text.length());
                StringBuilder finalText = new StringBuilder(text.substring(j, end));
                while (finalText.length() < maxPrintLength - 3){
                    finalText.append(" ");
                }
                finalText.append("║");
                System.out.println("║ " + finalText);
                lastPrint = "║ " + finalText;
                //println(text.substring(j, end));
                j += k;
            }
        }
    }

    public static void print(String text){
        System.out.print("╠ " + text);
        lastPrint = "╠ " + text;
    }
    public static void println(String text){
        if (text.equals("╝") || text.equals("╣") || text.equals("╗") || text.equals(" ")){
            String finalText = null;
            switch (text){
                case "╝":
                    finalText = "╠" + "═".repeat(maxPrintLength - 2) + "╝";
                    System.out.println(finalText);
                    lastPrint = finalText;
                    break;
                case "╣":
                    finalText = "╠" + "═".repeat(maxPrintLength - 2) + "╣";
                    System.out.println(finalText);
                    lastPrint = finalText;
                    break;
                case "╗":
                    finalText = "╠" + "═".repeat(maxPrintLength - 2) + "╗";
                    System.out.println(finalText);
                    lastPrint = finalText;
                    break;
                case " ":
                    finalText = "║" + " ".repeat(maxPrintLength - 2) + "║";
                    System.out.println(finalText);
                    lastPrint = finalText;
                    break;
            }
        }
        else {
            StringBuilder finalText = new StringBuilder(text);
            while (finalText.length() < maxPrintLength - 3){
                finalText.append(" ");
            }
            finalText.append("║");
            System.out.println("╠ " + finalText);
            lastPrint = "╠ " + finalText;
        }
    }
    public static void printf(String format, Object... args) {
        String formattedText = String.format(format, args);
        int maxLength = maxPrintLength - 2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formattedText);
        int spacesNeeded = maxLength - formattedText.length() - 1; // -1 pour le caractère final "║"
        for (int i = 0; i < spacesNeeded; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("║");
        System.out.println("╠ " + stringBuilder);
        lastPrint = "╠ " + stringBuilder;
    }

    public static int getMaxPrintLength() {
        return maxPrintLength;
    }

    public static Database getDatabase() {
        return database;
    }
}