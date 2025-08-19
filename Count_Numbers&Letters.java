import java.util.Scanner;

public class App {
    private static final Scanner user = new Scanner(System.in); // Scanner instance
    private static final String WELCOME_MESSAGE = "\nWELCOME TO THE APPLICATION!"; // Welcome message
    private static final String DESCRIPTION = "\n This program separates letters and numbers, counts them and determines their types."; // Description of the program
    private static final String EXIT_MESSAGE = "\nMaraming Salamat sa pag gamit!"; // Exit message

    // Method to get user input
    private static String getInput() {
        System.out.print("Enter text: ");
        return user.nextLine();
    }

    // Method to separate letters and numbers from the input
    private static void separateLettersAndNumbers(String input) {
        String letters = ""; // String to hold letters
        String wholeNumbers = ""; // String to hold whole numbers
        String decimals = ""; // String to hold decimal numbers
        boolean decimalFound = false; // Flag to check if a decimal point has been found

        // Loop through each character in the input string
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                letters += c;
            } else if (Character.isDigit(c)) {
                if (decimalFound) {
                    decimals += c;
                } else {
                    wholeNumbers += c;
                }
            } else if (c == '.' && wholeNumbers.length() > 0 && !decimalFound) {
                decimalFound = true;
            }
        }

        System.out.println("\nLetters: " + letters + ", count = " + letters.length());
        System.out.println("Whole Numbers: " + wholeNumbers + ", count = " + wholeNumbers.length());
        System.out.println("Decimals: " + decimals + ", count = " + decimals.length());

        String numberValue = wholeNumbers;
        if (decimalFound && decimals.length() > 0) {
            numberValue += "." + decimals;
        }

        if (wholeNumbers.length() > 0 || decimals.length() > 0) {
            String type;
            if (decimalFound && decimals.length() > 7) {
                type = "double";
            } else {
                type = getNumberType(numberValue);
            }
            System.out.println("Number value: " + numberValue + " (Type: " + type + ")\n");
        }
    }

    // Method to determine the type of number
    private static String getNumberType(String numStr) {
        try {
            byte b = Byte.parseByte(numStr);
            return "byte";
        } catch (NumberFormatException e) { }
        try {
            short s = Short.parseShort(numStr);
            return "short";
        } catch (NumberFormatException e) { }
        try {
            int i = Integer.parseInt(numStr);
            return "int";
        } catch (NumberFormatException e) { }
        try {
            long l = Long.parseLong(numStr);
            return "long";
        } catch (NumberFormatException e) { }
        try {
            float f = Float.parseFloat(numStr);
            return "float";
        } catch (NumberFormatException e) { }
        try {
            double d = Double.parseDouble(numStr);
            return "double";
        } catch (NumberFormatException e) { }
        return "unknown";
    }

    // Main method to run the application
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE); // Print welcome message
        System.out.println(DESCRIPTION); // Print description of the program

        //Loop to allow multiple inputs
        while (true) {
            String input = getInput(); // Get user input
            System.out.println("\nYou entered: " + input); // Display the input
            separateLettersAndNumbers(input); // Separate and count letters and numbers

            System.out.print("Do you want to try again? (yes/no): ");
            String answer = user.nextLine().trim().toLowerCase();

            while (!answer.equals("yes") && !answer.equals("no")) {
                System.out.println("Invalid input! Please enter 'yes' or 'no'.");
                System.out.print("Do you want to try again? (yes/no): ");
                answer = user.nextLine().trim().toLowerCase();
            }

            if (answer.equals("no")) {
                break;
            }
        }

        System.out.println(EXIT_MESSAGE); // Print exit message
    }
}
