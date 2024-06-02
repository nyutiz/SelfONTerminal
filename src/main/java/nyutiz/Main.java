package nyutiz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static nyutiz.Database.initializeDatabase;
import static nyutiz.Printer.*;

public class Main {

    static int maxPrintLength;
    private static String lastPrint;

    private static Scanner scanner = new Scanner(System.in);

    private static Database database;
    private static String currentDatabase;
    private static Map<String, Database> dbMap = new LinkedHashMap<>();

    private static String config;
    private static String baseFile;
    private static String passwordDB;
    private static String taskManagerDB;

    public static void main(String[] args) throws Exception {
        //System.out.println("╔══════════════════════════════════════════════════════════╗");

        try {
            if (args[0] != null){
                database = new Database();
                database.loadDatabase(args[0]);
                dbMap.put(baseFile, database);
                currentDatabase = baseFile;
                baseFile = args[0];
            }
        } catch (Exception e) {

        }

        setMaxPrintLength(70);

        loadConfig("db.config");

        printWith(" Welcome to SelfON ", "╔", "╗");
        println("0 : Load Database / Config");
        println("1 : Password Manager (not implemented yet)");
        println("2 : Task Manager (not implemented yet)");
        println("3 : Database Manager");

        println("╣");

        start();
    }

    public static void start() throws Exception {
        printWith(" Loaded Database : " + dbMap.keySet() + " ", " ");
        printWith(" Current : " + currentDatabase + " " ," ");
        println("╝");
        System.out.print("╠═ ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        lastPrint = " ";
        switch (choice){
            case 0:


                println("╗");
                println("0 : Load Database");
                println("1 : Load Config");
                println("2 : Return to the menu");
                System.out.print("╠═ ");

                choice = scanner.nextInt();
                scanner.nextLine();
                lastPrint = " ";

                switch (choice){
                    case 0:
                        print("Please provide the Database path : ");
                        database = new Database();
                        baseFile = scanner.nextLine();
                        if (database.loadDatabase(baseFile))
                        {
                            dbMap.put(baseFile, database);
                            currentDatabase = baseFile;
                            println("Database *" + baseFile + "* loaded");
                            println("╣");
                            start();
                            break;
                        }
                        else{
                            println("Database *" + baseFile + "* NOT loaded");
                            println("╣");
                            start();
                            break;
                        }
                    case 1:
                        print("Please provide the Config file path : ");
                        String path = scanner.nextLine();

                        if (loadConfig(path))
                        {
                            config = path;
                            println("Config file *" + config + "* loaded");
                            println("PasswordDB : " + passwordDB + "  " + "TaskManagerDB : " + taskManagerDB);
                            println("╣");
                            start();
                            break;
                        }
                        else{
                            println("Config file *" + config + "* NOT loaded");
                            println("╣");
                            start();
                            break;
                        }
                    case 2:
                        println("╣");
                        start();
                        break;
                }

                print("Please provide the Database path : ");
                database = new Database();
                baseFile = scanner.nextLine();
                if (database.loadDatabase(baseFile))
                {
                    dbMap.put(baseFile, database);
                    currentDatabase = baseFile;
                    println("Database *" + baseFile + "* loaded");
                    println("╣");
                    start();
                    break;
                }
                else{
                    println("Database *" + baseFile + "* NOT loaded");
                    println("╣");
                    start();
                    break;
                }

            case 1:
                if (database == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                    start();
                    break;
                }
                start();
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
                start();
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
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice){
                        case 0:
                            print("Please provide the Database path : ");
                            database = new Database();
                            baseFile = scanner.nextLine();
                            if (database.loadDatabase(baseFile))
                            {
                                dbMap.put(baseFile, database);
                                currentDatabase = baseFile;
                                println("Database *" + baseFile + "* loaded");
                                println("╣");
                                start();
                                break;
                            }
                            else{
                                println("Database *" + baseFile + "* NOT loaded");
                                println("╣");
                                start();
                                break;
                            }
                        case 1:
                            print("Enter database name: ");
                            String dbName = scanner.nextLine();
                            lastPrint = " ";
                            print("Enter data (space-separated): ");
                            String[] datas = scanner.nextLine().replaceAll(" ", ";").split(";");
                            lastPrint = " ";
                            println("Database Name: " + dbName);
                            println("Data: " + Arrays.toString(datas));

                            initializeDatabase(dbName, datas);

                            println("You need to load the database after creating one");
                            println("You can open the created file to enter data manually");
                            println("╣");
                            start();
                            break;
                        case 2:
                            start();
                            break;
                        default:
                            start();
                            break;
                    }
                }
                println("╗");
                println("0 : Select Current Database");
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
                    case 0:
                        println("╣");
                        print("Enter the Database Name : ");
                        String current = scanner.nextLine();
                        if (dbMap.containsKey(current)){
                            println("Database Selected");
                            currentDatabase = current;
                            println("╣");
                            start();
                            break;
                        }
                        else {
                            println("Database Not Found in Loaded Database");
                            println("╣");
                            start();
                            break;
                        }

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
                        println("╣");
                        database.displayData();
                        println("╣");
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
                        println("╣");
                        start();
                        break;
                    case 3:
                        printWith(" Number of Datas : " + database.dataList.size() + " ");
                        database.displayData();
                        println("╣");
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
                        println("╣");
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

                        println("You need to load the database after creating one");
                        println("╣");
                        start();
                        break;
                    default:
                        println("╣");
                        start();
                }
                break;

            default:

                break;
        }
    }

    public static boolean loadConfig(String path) {
        Map<String, String> conf = new LinkedHashMap<>();
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.forEach((key, value) -> {
                conf.put((String) key, (String) value);
                if (key.equals("passwordDB")){
                    passwordDB = value.toString();
                }
                else if(key.equals("taskManagerDB")){
                    taskManagerDB = value.toString();
                }
            });
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }


    public static int getMaxPrintLength() {
        return maxPrintLength;
    }

    public static Database getDatabase() {
        return database;
    }

}