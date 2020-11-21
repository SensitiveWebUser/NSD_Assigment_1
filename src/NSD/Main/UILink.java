package NSD.Main;

import java.util.Scanner;

public class UILink {

    private DataImport dataIn = new DataImport();
    private DataExport dataOut = new DataExport();

    public void printOut(String output) {
        System.out.println(output);
    }

    public int inputToInt() {

        Scanner _Scanner = new Scanner(System.in);
        String input =  _Scanner.nextLine();
        int result;
        try{
            result = Integer.parseInt(input);
        }catch (Exception e) {
            printOut("Bad input");
            return -1;
        }
        return result;

    }

    public String inputToStr() {

        Scanner _Scanner = new Scanner(System.in);
        String input;
        try{
            input =  _Scanner.nextLine();
        }catch (Exception e) {
            printOut("Bad input");
            return "";
        }
        return input;

    }

    public void mainMenu() {

        boolean success = false;
        int userOption;

        while (!success) {

            success = true;

            printOut("===Welcome to the main menu===");
            printOut(" 1) Load data");
            printOut(" 2) Get users average reviews");
            printOut(" 3) Get users average reviews");
            printOut(" 4) Get movie average reviews");
            printOut(" 5) Export last result to file");
            printOut(" 6) Exit");

            userOption = inputToInt();

            switch(userOption) {

                case 1:
                        dataIn.importAll();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 6:
                    printOut("Hope to see you soon :).");
                    System.exit(0);
                    break;

                default:
                    success = false;

            }

        }

    }

}
