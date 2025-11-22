package FinalProject;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Manager {

    static int ManID;
    private int ManagerID;
    private String Firstname, Lastname;

    Manager(int ManagerID, String Firstname, String Lastname){

        this.ManagerID = ManagerID;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
    }
    static boolean menuchecker2 = false;

    public static void CancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║             RESERVATION CANCELLATION             ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
        System.out.print("Enter Client ID to cancel: ");
        String clientID = scanner.nextLine().trim();

        if (clientID.isEmpty()) {
            System.out.println("Client ID cannot be empty.");
            return;
        }

        File reserveFile = new File("RESERVE.txt");
        ArrayList<String> allRecords = new ArrayList<>();
        boolean found = false;

        try (Scanner fileScanner = new Scanner(reserveFile)) {
            StringBuilder record = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                record.append(line).append("\n");

                if (line.equals("--------------------------------------------------")) {
                    allRecords.add(record.toString());
                    record = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading RESERVE.txt: " + e.getMessage());
        }

        ArrayList<String> updatedRecords = new ArrayList<>();

        for (String record : allRecords) {
            if (record.contains("ClientID: " + clientID)) {
                found = true;
                // Save to CANCELLED.txt
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("CANCELLED.txt", true))) {
                    bw.write("ClientID: " + clientID + " | Cancelled at: " + java.time.LocalDateTime.now());
                    bw.newLine();
                } catch (IOException e) {
                    System.out.println("Error saving cancelled reservation: " + e.getMessage());
                }

                System.out.println("\nReservation for ClientID " + clientID + " has been cancelled successfully!");
            } else {
                updatedRecords.add(record);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("RESERVE.txt"))) {
            for (String rec : updatedRecords) {
                bw.write(rec);
            }
        } catch (IOException e) {
            System.out.println("Error updating RESERVE.txt: " + e.getMessage());
        }

        if (!found) {
            System.out.println("\nReservation not found for ClientID: " + clientID);
        }

        System.out.println("\nType anything to return to the Manager Menu.");
        scanner.nextLine();
        MainCode.clearscreen();
    }

    public static void Manager(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean confirmation3 = false;
        boolean IDChecker2 = false;

        MainCode.clearscreen();
        MainCode.loadingscreen();
        MainCode.clearscreen();

        while(!confirmation3){
            try{
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║               ACTION CONFIRMATION                ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║  You selected: Manager                           ║");
                System.out.println("║  Do you want to continue?                        ║");
                System.out.println("║                                                  ║");
                System.out.println("║  > Yes, proceed to login                         ║");
                System.out.println("║  > No, go back                                   ║");
                System.out.println("╚══════════════════════════════════════════════════╝\n");
                System.out.print("Enter your choice: ");
                String actionchoice = scanner.next();

                if(actionchoice.equalsIgnoreCase("yes")){
                    confirmation3 = true;
                    MainCode.clearscreen();
                    MainCode.loadingscreen2();
                    MainCode.clearscreen();
                }else if(actionchoice.equalsIgnoreCase("no")){
                    MainCode.main(args);
                }else{
                    System.out.println("Incorrect Choice. Please Try Again.");
                    confirmation3 = false;
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

        Manager m1 = new Manager(2025122560, "Allen", "Barcelos");

        while(!IDChecker2){
            try {
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║            WELCOME TO CASA DE ALLEN              ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║   Please enter your login credentials below.     ║");
                System.out.println("╚══════════════════════════════════════════════════╝\n");
                System.out.print("Enter your ID: ");
                ManID = scanner.nextInt();
                
                if(ManID == m1.ManagerID){
                    IDChecker2 = true;
                    System.out.println();
                    MainCode.loadingscreen2();
                    MainCode.checkingdatabasescreen();
                    MainCode.clearscreen();
                    MainCode.verifyingscreen(); 
                }else{
                    IDChecker2 = false;
                    System.out.println("Incorrect ID Number. Please Try Again.\n");
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

        MainCode.Barcelos();

        while(!menuchecker2){
                System.out.println("╔══════════════════════════════════════════════════╗");
                System.out.println("║                   MANAGER MENU                   ║");
                System.out.println("╠══════════════════════════════════════════════════╣");
                System.out.println("║  1. View Records                                 ║");
                System.out.println("║  2. Cancellation of Reservations                 ║");
                System.out.println("║  3. Log out                                      ║");
                System.out.println("╚══════════════════════════════════════════════════╝");

                System.out.print("\nEnter your choice: ");
                MainCode.choice = scanner.nextInt();

            try{
                switch(MainCode.choice){
                    case 1: Receptionist.ViewRecords();
                    break;

                    case 2: CancelReservation();
                    break;

                    case 3:MainCode.clearscreen();
                    System.out.println("╔══════════════════════════════════════════════════╗");
                    System.out.println("║               ACTION CONFIRMATION                ║");
                    System.out.println("╠══════════════════════════════════════════════════╣");
                    System.out.println("║  You selected: LOG-OUT                           ║");
                    System.out.println("║  Are you sure you want to log out?               ║");
                    System.out.println("║                                                  ║");
                    System.out.println("║  > Yes, log out                                  ║");
                    System.out.println("║  > No, go back                                   ║");
                    System.out.println("╚══════════════════════════════════════════════════╝\n");
                    System.out.print("Enter your choice: ");
                    String logoutchoice = scanner.next();
                    if(logoutchoice.equalsIgnoreCase("yes")){
                        MainCode.clearscreen();
                        MainCode.lougoutscreen();
                        MainCode.main(args);
                    }else if(logoutchoice.equalsIgnoreCase("no")){
                        menuchecker2 = false;
                        MainCode.clearscreen();
                    }
                    break;

                    default: System.out.println("Invalid Choice. Please Try again");
                    scanner.next();
                    
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
