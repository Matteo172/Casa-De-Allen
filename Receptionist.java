package FinalProject;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receptionist{
    static int RecepID;
    private int ReceptionistID;
    private String Firstname, LastName;

    Receptionist(int ReceptionistID, String Firstname, String LastName){
        this.ReceptionistID = ReceptionistID;
        this.Firstname = Firstname;
        this.LastName = LastName;
    }

    public static void ViewRecords(){



    }

    public static void SortingandFiltering(){


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

                    case 2: SortingandFiltering();
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
