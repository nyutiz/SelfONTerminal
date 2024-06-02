package nyutiz;

import java.io.*;
import java.util.*;

import static nyutiz.Main.getMaxPrintLength;
import static nyutiz.Printer.println;

public class Database {
    public List<Data> dataList = new ArrayList<>();
    public List<String> descriptors = new ArrayList<>();
    public String baseFile = "database.txt";
    public int key = 0;

    public void app() throws Exception {
        String password = "Nyutiz";
        key = 0;
        for (int i = 0; i < password.length(); i++) {
            int asciiValue = password.charAt(i);
            key = key + asciiValue;
        }
        start();
    }

    void start() throws Exception {
        Database database = new Database();
        database.loadDatabase(baseFile);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 Créer un compte");
        System.out.println("2 Modifier un compte");
        System.out.println("3 Afficher les comptes");
        int select;
        String id;
        select = scanner.nextInt();
        scanner.nextLine();

        switch (select) {
            case 1:
                id = database.generateRandomId();
                System.out.println("Id : " + id);
                Data newAccount = new Data();
                newAccount.setData("Id", id);

                for (String descriptor : database.getDescriptors()) {
                    if (!descriptor.equals("Id")) {
                        System.out.print(descriptor + " : ");
                        String value = scanner.nextLine();
                        newAccount.setData(descriptor, value);
                    }
                }
                database.addData(newAccount);
                database.displayData();
                start();
                break;
            case 2:
                System.out.print("Id : ");
                id = scanner.nextLine();
                System.out.println("Modifier ");
                for (int i = 1; i < database.getDescriptors().size(); i++) {
                    System.out.println((i) + " " + database.getDescriptors().get(i));
                }
                int modifyChoice = scanner.nextInt();
                scanner.nextLine();

                if (modifyChoice > 0 && modifyChoice < database.getDescriptors().size()) {
                    String fieldToModify = database.getDescriptors().get(modifyChoice);
                    System.out.print("Nouveau " + fieldToModify + ": ");
                    String newValue = scanner.nextLine();
                    database.updateData(id, fieldToModify, newValue);
                } else {
                    System.out.println("Choix invalide");
                }
                start();
                break;
            case 3:
                database.displayData();
                start();
                break;
            default:
                start();
        }
    }

    public List<String> getDescriptors() {
        return descriptors;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public String generateRandomId(){
        Random random = new Random();
        while (true) {
            int id = 1000000 + random.nextInt(9000000);
            if (!isIdExists(String.valueOf(id))) {
                return String.valueOf(id);
            }
        }
    }

    public void loadDatabase(String baseFile) throws Exception {
        this.baseFile = baseFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(baseFile))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (i == 0) {
                    for (String part : parts) {
                        descriptors.add(part);
                    }
                    i++;
                    continue;
                }
                Data data = new Data();
                for (int j = 0; j < parts.length; j++) {
                    data.setData(descriptors.get(j), parts[j]);
                }
                dataList.add(data);
                i++;
            }
        }
    }

    public void sortData(String descriptor) {
        Collections.sort(dataList, new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                String value1 = d1.getData(descriptor);
                String value2 = d2.getData(descriptor);
                return value1.compareTo(value2);
            }
        });
    }

    public void saveDatabase(String baseFile) throws Exception {
        try (FileWriter writer = new FileWriter(baseFile)) {
            writer.write(String.join(";", descriptors) + "\n");
            for (Data data : dataList) {
                List<String> values = new ArrayList<>();
                for (String descriptor : descriptors) {
                    values.add(data.getData(descriptor));
                }
                writer.write(String.join(";", values) + "\n");
            }
        }
    }

    public boolean isIdExists(String id) {
        return findClientById(id) != null;
    }


    public Data findClientById(String id) {
        for (Data data : dataList) {
            if (id.equals(data.getData(descriptors.get(0)))) {
                return data;
            }
        }
        return null;
    }

    public void addData(Data data) throws Exception {
        dataList.add(data);
        saveDatabase(baseFile);
        println("Nouveau client ajouté.");
    }

    public void updateData(String id, String key, String newValue) throws Exception {
        Data client = findClientById(id);
        if (client != null) {
            client.setData(key, newValue);
            saveDatabase(baseFile);
            println(key + " mis à jour.");
        } else {
            println("Aucun client avec l'ID '" + id + "' trouvé.");
        }
    }

    public void displayData() {
        StringBuilder a = new StringBuilder();
        for (Data data : dataList) {
            a.append("╠ ");
            for (String descriptor : descriptors) {
                a.append(data.getData(descriptor)).append("; ");
            }
            while (a.length() < getMaxPrintLength() - 1) {
                a.append(" ");
            }
            a.append("║");
            System.out.println(a);
            a.setLength(0);
        }
        println("╝");
    }

    public static void initializeDatabase(String fileName, String... datas) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Id;" + String.join(";", datas) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBaseFile(String baseFile) {
        this.baseFile = baseFile;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void setDescriptors(List<String> descriptors) {
        this.descriptors = descriptors;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getBaseFile() {
        return baseFile;
    }

    public int getKey() {
        return key;
    }
}
