package nyutiz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static nyutiz.Database.initializeDatabase;
import static nyutiz.Printer.*;

public class Main {

    static int maxPrintLength;

    private static final Scanner scanner = new Scanner(System.in);

    private static Database database;
    private static String currentDatabase;
    private static final Map<String, Database> dbMap = new LinkedHashMap<>();

    private static String config;
    private static String baseFile;
    private static Database passwordDB;
    private static Database taskManagerDB;

    public static void main(String[] args) throws Exception {
        /*
        System.out.println("╔══════════════════════════════════════════════════════════╗");
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
         */

        setMaxPrintLength(70);

        printWith(" Welcome to SelfON ", "╔", "╗");
        println("0 : Load Database / Config");
        println("1 : Password Manager");
        println("2 : Task Manager");
        println("3 : Database Manager");

        start();
    }

    public static void start() throws Exception {
        println("╣");
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
                System.out.print("╠══ ");

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
                            String filename = getLastSegment(baseFile);
                            dbMap.put(filename, database);
                            currentDatabase = filename;
                            println("Database *" + baseFile + "* loaded");
                        }
                        else{
                            println("Database *" + baseFile + "* NOT loaded");
                            database = null;
                        }
                        start();
                        break;
                    case 1:
                        print("Please provide the Config file path : ");
                        String path = scanner.nextLine();

                        if (loadConfig(path))
                        {
                            config = getLastSegment(path);
                            println("Config file *" + config + "* loaded");
                            println("PasswordDB : " + passwordDB + "  " + "TaskManagerDB : " + taskManagerDB);
                        }
                        else{
                            println("Config file *" + config + "* NOT loaded");
                        }
                        start();
                        break;
                    case 2:
                        start();
                        break;
                }
            case 1:
                if (passwordDB == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                }
                else {
                    println("╗");
                    println("0 : Register Credentials");
                    println("1 : Show a Credential");
                    println("2 : Edit Credentials");
                    println("3 : Delete a Credential");
                    System.out.print("╠══ ");

                    choice = scanner.nextInt();
                    scanner.nextLine();
                    String id;
                    lastPrint = " ";

                    Data data;
                    String value;

                    println("╣");
                    switch (choice){
                        case 0:
                            id = passwordDB.generateRandomId();
                            println("Id : " + id);
                            Data newCredential = new Data();
                            newCredential.setData("Id", id);

                            for (String descriptor : passwordDB.getDescriptors()) {
                                if (!descriptor.equals("Id")) {
                                    print(descriptor + " : ");
                                    value = scanner.nextLine();
                                    newCredential.setData(descriptor, value);
                                }
                            }
                            passwordDB.addData(newCredential);
                            println("╣");
                            passwordDB.displayData();
                            break;
                        case 1:
                            print("Please provide the Id or The Service : ");
                            value = scanner.nextLine();
                            List<Data> dataList = new ArrayList<>();

                            if (value.length() == 7) {
                                try {
                                    Integer.parseInt(value);
                                    data = passwordDB.findDataById(value);
                                    if (data != null) {
                                        dataList.add(data);
                                    }
                                } catch (NumberFormatException e) {
                                    println("The provided ID is not a valid number.");
                                }
                            } else {
                                dataList = passwordDB.findDataByDescriptor(value);
                            }

                            if (!dataList.isEmpty()) {
                                println("Data found:");
                                for (Data d : dataList) {
                                    println(d.getDatas());
                                }
                            } else {
                                println("No data found.");
                            }

                            break;
                        case 2:
                            print("Id : ");
                            id = scanner.nextLine();
                            data = passwordDB.findDataById(id);
                            println(data.getDatas());
                            println("Edit : ");
                            for (int i = 1; i < passwordDB.getDescriptors().size(); i++) {
                                println((i) + " " + passwordDB.getDescriptors().get(i));
                            }
                            System.out.print("╠══ ");
                            lastPrint = " ";
                            int modifyChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (modifyChoice > 0 && modifyChoice < passwordDB.getDescriptors().size()) {
                                String fieldToModify = passwordDB.getDescriptors().get(modifyChoice);
                                print("New " + fieldToModify + " : ");
                                String newValue = scanner.nextLine();
                                passwordDB.updateData(id, fieldToModify, newValue);
                            } else {
                                println("Invalid");
                            }
                            break;

                        case 3:
                            print("Id : ");
                            id = scanner.nextLine();
                            println("╗");
                            printWith(" Are you sure you want to delete this data ? ", "!");
                            println("╝");
                            print("Type YES or NO : ");
                            value = scanner.nextLine();
                            if (value.equals("YES"))
                            {
                                passwordDB.deleteData(id);
                                println("Data deleted");
                            }
                            else{
                                println("Nothing deleted");
                            }
                            break;
                    }
                    start();
                }
                start();
                break;

            case 2:
                if (taskManagerDB == null){
                    println("╗");
                    printWith(" Please load the database or create one ", "!");
                    println("╝");
                }
                else {
                    println("╗");
                    println("0 : Add Task");
                    println("1 : Show all Tasks");
                    println("2 : Show a Task");
                    println("3 : Edit Task");
                    println("4 : Sort Task");
                    println("5 : Delete a Task");
                    System.out.print("╠══ ");

                    choice = scanner.nextInt();
                    scanner.nextLine();
                    String id;
                    lastPrint = " ";

                    Data data;
                    String value;

                    println("╣");
                    switch (choice){
                        case 0:
                            id = taskManagerDB.generateRandomId();
                            Data newTask = new Data();
                            newTask.setData("Id", id);
                            println("Id : " + id);
                            lastPrint = " ";
                            Integer scores = 1;
                            Map<String, Integer> priorityScores = new HashMap<>();
                            priorityScores.put("high", 100);
                            priorityScores.put("medium", 50);
                            priorityScores.put("low", 10);
                            Map<String, Integer> statusScores = new HashMap<>();
                            statusScores.put("to do", 75);
                            statusScores.put("in progress", 50);
                            statusScores.put("completed", 0);

                            for (String descriptor : taskManagerDB.getDescriptors()) {
                                if (!descriptor.equals("Id") && !descriptor.equals("Score")) {
                                    if (taskManagerDB.descriptorOptions.containsKey(descriptor)) {
                                        List<String> options = taskManagerDB.descriptorOptions.get(descriptor);
                                        println("Choose a " + descriptor + ":");
                                        for (int i = 0; i < options.size(); i++) {
                                            println((i + 1) + ": " + options.get(i));
                                        }
                                        System.out.print("╠══ ");
                                        lastPrint = " ";
                                        choice = scanner.nextInt();
                                        scanner.nextLine();

                                        value = options.get(choice - 1);
                                        newTask.setData(descriptor, value);

                                        if (priorityScores.containsKey(value)) {
                                            scores *= priorityScores.get(value);
                                        }
                                        if (statusScores.containsKey(value)) {
                                            scores *= statusScores.get(value);
                                        }
                                        newTask.setData("Score", String.valueOf(scores));
                                    } else {
                                        print(descriptor + ": ");
                                        value = scanner.nextLine();
                                        newTask.setData(descriptor, value);
                                    }
                                }
                            }
                            taskManagerDB.addData(newTask);
                            println("Task added successfully!");
                            lastPrint = " ";
                            taskManagerDB.sortDataDecrease(taskManagerDB.getDescriptors().get(1));
                            taskManagerDB.displayData();
                            taskManagerDB.saveDatabase(taskManagerDB.baseFile);
                            break;

                        case 1:
                            printWith(" Number of Datas : " + taskManagerDB.dataList.size() + " ");
                            taskManagerDB.displayData();
                            break;
                        case 2:
                            print("Please provide the Id or the Title of the task : ");
                            value = scanner.nextLine();
                            List<Data> dataList = new ArrayList<>();

                            if (value.length() == 7) {
                                try {
                                    Integer.parseInt(value);
                                    data = taskManagerDB.findDataById(value);
                                    if (data != null) {
                                        dataList.add(data);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("The provided ID is not a valid number.");
                                }
                            } else {
                                dataList = taskManagerDB.findDataByDescriptor(value);
                            }

                            if (!dataList.isEmpty()) {
                                println("Data found:");
                                for (Data d : dataList) {
                                    println(d.getDatas());
                                }
                            } else {
                                println("No data found.");
                            }

                            break;
                        case 3:
                            print("Id : ");
                            id = scanner.nextLine();
                            data = taskManagerDB.findDataById(id);
                            println(data.getDatas());
                            println("Edit : ");
                            for (int i = 1; i < taskManagerDB.getDescriptors().size(); i++) {
                                println((i) + " " + taskManagerDB.getDescriptors().get(i));
                            }
                            System.out.print("╠══ ");
                            lastPrint = " ";
                            int modifyChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (modifyChoice > 0 && modifyChoice < taskManagerDB.getDescriptors().size()) {
                                String fieldToModify = taskManagerDB.getDescriptors().get(modifyChoice);
                                print("New " + fieldToModify + " : ");
                                String newValue = scanner.nextLine();
                                taskManagerDB.updateData(id, fieldToModify, newValue);
                            } else {
                                println("Invalid");
                            }
                            break;

                        case 4:
                            taskManagerDB.sortDataDecrease(taskManagerDB.getDescriptors().get(1));
                            taskManagerDB.displayData();
                            taskManagerDB.saveDatabase(taskManagerDB.baseFile);
                            break;

                        case 5:
                            print("Id : ");
                            id = scanner.nextLine();
                            println("╗");
                            printWith(" Are you sure you want to delete this data ? ", "!");
                            println("╝");
                            print("Type YES or NO : ");
                            value = scanner.nextLine();
                            if (value.equals("YES"))
                            {
                                taskManagerDB.deleteData(id);
                                println("Data deleted");
                            }
                            else{
                                println("Nothing deleted");
                            }
                            break;
                    }
                    start();
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
                    println("╣");
                    switch (choice){
                        case 0:
                            print("Please provide the Database path : ");
                            database = new Database();
                            baseFile = scanner.nextLine();
                            if (database.loadDatabase(baseFile))
                            {
                                String filename = getLastSegment(baseFile);
                                dbMap.put(filename, database);
                                currentDatabase = filename;
                                println("Database *" + baseFile + "* loaded");
                            }
                            else{
                                println("Database *" + baseFile + "* NOT loaded");
                                database = null;
                            }
                            break;
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
                            break;
                        case 2:
                            //a
                            start();
                            break;
                        default:
                            start();
                            break;
                    }
                    start();
                }
                println("╗");
                println("0 : Select Current Database");
                println("1 : Create Account");
                println("2 : Edit Account");
                println("3 : Show Accounts");
                println("4 : Delete Account");
                println("5 : Sort Database");
                println("6 : Create Database");
                println("7 : Return to the menu");
                System.out.print("╠══ ");
                lastPrint = " ";
                int select;
                String id;
                Data data;
                String value;
                select = scanner.nextInt();
                scanner.nextLine();
                println("╣");
                switch (select) {
                    case 0:
                        print("Enter the Database Name : ");
                        String current = scanner.nextLine();
                        if (dbMap.containsKey(current)){
                            println("Database Selected");
                            currentDatabase = current;
                            database = dbMap.get(currentDatabase);
                        }
                        else {
                            println("Database Not Found in Loaded Database");
                        }

                        break;

                    case 1:
                        id = database.generateRandomId();
                        println("Id : " + id);
                        Data newAccount = new Data();
                        newAccount.setData("Id", id);

                        for (String descriptor : database.getDescriptors()) {
                            if (!descriptor.equals("Id")) {
                                print(descriptor + " : ");
                                value = scanner.nextLine();
                                newAccount.setData(descriptor, value);
                            }
                        }
                        database.addData(newAccount);
                        println("╣");
                        database.displayData();

                        break;
                    case 2:
                        print("Id : ");
                        id = scanner.nextLine();
                        data = database.findDataById(id);
                        println(data.getDatas());
                        println("Edit : ");
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
                            println("Invalid ");
                        }
                        break;
                    case 3:
                        printWith(" Number of Datas : " + database.dataList.size() + " ");
                        database.displayData();
                        break;
                    case 4:
                        print("Id : ");
                        id = scanner.nextLine();
                        println("╗");
                        printWith(" Are you sure you want to delete this data ? ", "!");
                        println("╝");
                        print("Type YES or NO : ");
                        value = scanner.nextLine();
                        if (value.equals("YES"))
                        {
                            database.deleteData(id);
                            println("Data deleted");
                        }
                        else{
                            println("Nothing deleted");
                        }
                        break;
                    case 5:
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
                            println("Invalid");
                        }
                        break;
                    case 6:
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
                        break;
                    case 7:
                        break;
                    default:
                        start();
                }
                start();
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
                if (value != null){
                    if (key.equals("passwordDB")){
                        String a = value.toString();
                        passwordDB = new Database();
                        if(passwordDB.loadDatabase(a)){

                            String filename = getLastSegment(a);
                            currentDatabase = filename;
                            dbMap.put(filename, passwordDB);
                            println("Database password *" + filename + "* loaded");
                        }
                    }
                    else if(key.equals("taskManagerDB")){
                        String a = value.toString();
                        taskManagerDB = new Database();
                        if(taskManagerDB.loadDatabase(a)){
                            String filename = getLastSegment(a);
                            currentDatabase = filename;
                            dbMap.put(filename, taskManagerDB);
                            println("Database taskManager *" + filename + "* loaded");
                        }
                    }
                }

            });
            return true;
        } catch (Exception ex) {
            println(ex.toString());
            return false;
        }
    }


    public static int getMaxPrintLength() {
        return maxPrintLength;
    }

    public static String getLastSegment(String path) {
        if (path != null){
            int lastIndex = path.lastIndexOf('\\');
            if (lastIndex != -1) {
                return path.substring(lastIndex + 1);
            } else {
                return path;
            }
        }
        else {
            return null;
        }

    }

}