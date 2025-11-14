package FinalProject;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receptionist{

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
        
        MainCode.clearscreen();
        MainCode.loadingscreen();
        MainCode.clearscreen();

        Receptionist r1 = new Receptionist(2025111257, "Alynere", "SDPT");

        while(!IDChecker){
            try {
                System.out.print("Enter your ID: ");
                int RecepID = scanner.nextInt();
                
                if(RecepID != r1.ReceptionistID){
                    System.out.println("Incorrect ID Number. Please Try Again.\n");
                    IDChecker = false;
                    System.out.println("Type Anything to continue.");
                    scanner.next();
                    MainCode.clearscreen();
                }else{
                    IDChecker = true;
                    System.out.println();
                    MainCode.loadingscreen();
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
        
        System.out.printf("Welcome Ms. %s %s\n", r1.Firstname, r1.LastName);
        boolean menuchecker = false;

        while(!menuchecker){
                System.out.println("Receptionist Menu\n");
                System.out.println("1. View Records");
                System.out.println("2. Sort Reservation and Filtering");
                System.out.println("3. Check-In Guests");
                System.out.println("4. Log out\n");
                System.out.print("Enter your choice: ");
                MainCode.choice = scanner.nextInt();

            try{
                switch(MainCode.choice){
                    case 1: ViewRecords();
                    break;

                    case 2:
                    break;

                    case 3:
                    break;

                    case 4: System.out.println("Are you sure you want to Log-out? y/n");
                    String logoutchoice = scanner.next();
                    if(logoutchoice.equals("y")){
                        MainCode.loadingscreen();
                        MainCode.main(args);
                    }else if(logoutchoice.equals("n")){
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