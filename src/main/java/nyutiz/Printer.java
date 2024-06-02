package nyutiz;

public class Printer {

    public static int maxPrintLength = 70;
    public static String lastPrint;

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
        if (space.equals(" ")){
            finalText.append("║");
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
                finalText.append("║");
            } else {
                finalText.append("╗");
            }
            System.out.println(finalText);
            lastPrint = finalText.toString();
        }
        else{
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

        if (text.length() > maxPrintLength) {
            printLarge(text);
        }
        else {
            if (text.equals("╝") || text.equals("╣") || text.equals("╗") || text.equals(" ") || text.equals("║")){
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
                    case "║":
                        finalText = "║" + "═".repeat(maxPrintLength - 2) + "║";
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

    public static void setMaxPrintLength(int maxPrintLength) {
        Printer.maxPrintLength = maxPrintLength;
        Main.maxPrintLength = maxPrintLength;
    }
}
