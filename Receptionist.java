package FinalProject;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Receptionist{
    static int RecepID;
    private int ReceptionistID;
    private String Firstname, LastName;

    Receptionist(int ReceptionistID, String Firstname, String LastName){
        this.ReceptionistID = ReceptionistID;
        this.Firstname = Firstname;
        this.LastName = LastName;
    }

    

    public static void ViewRecords() {
        Scanner sc = new Scanner(System.in);
        String fileName = "RESERVE.txt";

        MainCode.clearscreen();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          VIEW RESERVATION RECORDS          ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                found = true;
            }

            if (!found) {
                System.out.println("No reservation records found.\n");
            }

        } catch (IOException e) {
            System.out.println("Error: Reservation file not found or cannot be opened.\n");
        }

        System.out.println("\nPress Enter to go back...");
        sc.nextLine();
        MainCode.clearscreen();
    }

    

    public static void SortingandFiltering() {
        Scanner sc = new Scanner(System.in);
        int sortChoice;

        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║            SORTING & FILTERING OPTIONS            ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. Sort Date (Ascending)                         ║");
            System.out.println("║  2. Sort Date (Descending)                        ║");
            System.out.println("║  3. Filter Payments (Highest to Lowest)           ║");
            System.out.println("║  4. Back to Receptionist Menu                     ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice: ");

            try {
                sortChoice = sc.nextInt();

                switch (sortChoice) {
                    case 1:
                        sortByDateAscending();
                        break;
                    case 2:
                        sortByDateDescending();
                        break;
                    case 3:
                        sortByPaymentDescending();
                        break;
                    case 4:
                        MainCode.clearscreen();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, numbers only.");
                sc.nextLine();
            }
        }
    }

    private static LocalDate extractDate(String record) {
        for (String line : record.split("\n")) {
            if (line.startsWith("Date:")) {
                String dateStr = line.replace("Date:", "").trim();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                    return LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return LocalDate.MIN;
    }

    private static void sortByDateAscending() {
        ArrayList<String> records = loadReservationFile();
        if (records.isEmpty()) { 
            noDataFoundMsg(); 
            return; 
        }
        records.sort(Comparator.comparing(Receptionist::extractDate));

        displaySorted(records, "SORTED BY DATE (ASCENDING)");
    }

    private static void sortByDateDescending() {
        ArrayList<String> records = loadReservationFile();
        if (records.isEmpty()) { noDataFoundMsg(); return; }

        records.sort(Comparator.comparing(Receptionist::extractDate).reversed());

        displaySorted(records, "SORTED BY DATE (DESCENDING)");
    }

    private static void sortByPaymentDescending() {
        ArrayList<String> records = loadReservationFile();
        if (records.isEmpty()) { noDataFoundMsg(); return; }

        // Sort based on payment made (convert last found Payment Made: value)
        records.sort((r1, r2) -> {
            double pay1 = extractPayment(r1);
            double pay2 = extractPayment(r2);
            return Double.compare(pay2, pay1);
        });

        displaySorted(records, "FILTERED BY HIGHEST PAYMENT");
    }

    private static double extractPayment(String record) {
        for (String line : record.split("\n")) {
            if (line.startsWith("Payment Made:")) {
                return Double.parseDouble(line.replace("Payment Made:", "").trim());
            }
        }
        return 0.0;
    }

    private static ArrayList<String> loadReservationFile() {
        ArrayList<String> list = new ArrayList<>();
        String fileName = "RESERVE.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line, block = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Date:")) {
                    if (!block.isEmpty()) list.add(block);
                    block = line + "\n";
                } else {
                    block += line + "\n";
                }
            }
            if (!block.isEmpty()) list.add(block);

        } catch (IOException e) {
            System.out.println("Error! Cannot read RESERVE.txt");
        }

        return list;
    }

    private static void displaySorted(ArrayList<String> sortedList, String title) {
        Scanner sc = new Scanner(System.in);
        MainCode.clearscreen();

        System.out.println("\n====== " + title + " ======\n");

        for (String block : sortedList) {
            System.out.println(block);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
        MainCode.clearscreen();
    }

    private static void noDataFoundMsg() {
        System.out.println("\nNo reservation records found!\n");
    }






    public static void CheckinGuest(){



    }

    public static void Receptionist(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean IDChecker = false;
        boolean menuchecker = false;
        boolean confirmation = false;
        
        MainCode.clearscreen();
        MainCode.loadingscreen();
        MainCode.clearscreen();

        while(!confirmation){
            try{
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║               ACTION CONFIRMATION                ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║  You selected: RECEPTIONIST                      ║");
                System.out.println("║  Do you want to continue?                        ║");
                System.out.println("║                                                  ║");
                System.out.println("║  1. Yes, proceed to login                        ║");
                System.out.println("║  2. No, go back                                  ║");
                System.out.println("╚══════════════════════════════════════════════════╝\n");
                System.out.print("Enter your choice: ");
                String actionchoice = scanner.next();

                if(actionchoice.equalsIgnoreCase("yes")){
                    confirmation = true;
                    MainCode.clearscreen();
                    MainCode.loadingscreen2();
                    MainCode.clearscreen();
                }else if(actionchoice.equalsIgnoreCase("no")){
                    MainCode.main(args);
                }else{
                    System.out.println("Incorrect Choice. Please Try Again.");
                    confirmation = false;
                    System.out.println("\nType Anything to continue.");
                    scanner.next();
                    MainCode.clearscreen();
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter your ID number.\n");
                System.out.println("Type Anything to continue.");
                scanner.nextLine();
                scanner.next();
                MainCode.clearscreen();
            }
        }

        Receptionist r1 = new Receptionist(2025111257, "Alynere", "SDPT");
        Receptionist r2 = new Receptionist(2025111256, "Kim", "Pestijo");

        while(!IDChecker){
            try {
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║             WELCOME TO CASA DE ALLEN             ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║   Please enter your login credentials below.     ║");
                System.out.println("╚══════════════════════════════════════════════════╝\n");
                System.out.print("Enter your ID: ");
                RecepID = scanner.nextInt();
                
                if(RecepID == r1.ReceptionistID || RecepID == r2.ReceptionistID){
                    IDChecker = true;
                    System.out.println();
                    MainCode.loadingscreen2();
                    MainCode.checkingdatabasescreen();
                    MainCode.clearscreen();
                    MainCode.verifyingscreen();
                }else{
                    System.out.println("Incorrect ID Number. Please Try Again.\n");
                    IDChecker = false;
                    System.out.println("Type Anything to continue.");
                    scanner.next();
                    MainCode.clearscreen();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter your ID number.\n");
                System.out.println("Type Anything to continue.");
                scanner.nextLine();
                scanner.next();
                MainCode.clearscreen();
            }
        }

        MainCode.clearscreen();
        
        if(RecepID == r1.ReceptionistID){
            MainCode.Alynere();
        }else if(RecepID == r2.ReceptionistID){
            MainCode.Kim();
        }

        while(!menuchecker){
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║                 RECEPTIONIST MENU                ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║  1. View Records                                 ║");
                System.out.println("║  2. Sort Reservation & Filtering                 ║");
                System.out.println("║  3. Check-In Guests                              ║");
                System.out.println("║  4. Log out                                      ║");
                System.out.println("╚══════════════════════════════════════════════════╝");

                System.out.print("\nEnter your choice: ");
                MainCode.choice = scanner.nextInt();

            try{
                switch(MainCode.choice){
                    case 1: ViewRecords();
                    break;

                    case 2: MainCode.clearscreen();
                    SortingandFiltering();
                    break;

                    case 3: CheckinGuest();
                    break;

                    case 4: 
                    MainCode.clearscreen();
                    System.out.println("╔══════════════════════════════════════════════════╗");
                    System.out.println("║               ACTION CONFIRMATION                ║");
                    System.out.println("╠══════════════════════════════════════════════════╣");
                    System.out.println("║  You selected: LOG-OUT                           ║");
                    System.out.println("║  Are you sure you want to log out?               ║");
                    System.out.println("║                                                  ║");
                    System.out.println("║  1. Yes, log out                                 ║");
                    System.out.println("║  2. No, go back                                  ║");
                    System.out.println("╚══════════════════════════════════════════════════╝\n");
                    System.out.print("Enter your choice: ");
                    String logoutchoice = scanner.next();
                    if(logoutchoice.equalsIgnoreCase("yes")){
                        MainCode.clearscreen();
                        MainCode.lougoutscreen();
                        MainCode.main(args);
                    }else if(logoutchoice.equalsIgnoreCase("no")){
                        menuchecker = false;
                        MainCode.clearscreen();
                    }
                    break;
                    
                    default: System.out.println("Invalid Choice. Please Try again");
                }
            }catch(InputMismatchException e){
                System.out.print("Invalid Input. Please Enter a number");
                System.out.println("Type Anything to continue.");
                scanner.nextLine();
                scanner.next();
                MainCode.clearscreen();
            }
        }
    }
}
