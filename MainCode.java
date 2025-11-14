package FinalProject;
import java.util.*;

public class MainCode {

    static int choice;

    public static void clearscreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void loadingscreen(){
        System.out.print("Loading");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
    }

    public static void lougoutscreen(){
        System.out.print("Log-outing");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
        try{
            Thread.sleep(800);
        }catch (InterruptedException e){
            } System.out.print(".");
    }

    static String type;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean checker = false;

        System.out.println("  ____          _          _        _       _          _       ");
        System.out.println(" / ___|   __ _ | |__   ___| | _____| | __ _| |__   ___| |_ ___ ");
        System.out.println("| |      / _` || '_ \\ / _ \\ |/ / _ \\ |/ _` | '_ \\ / _ \\ __/ __|");
        System.out.println("| |___  | (_| || | | |  __/   <  __/ | (_| | | | |  __/ |_\\__ \\");
        System.out.println(" \\____|  \\__,_||_| |_|\\___|_|\\_\\___|_|\\__, |_| |_|\\___|\\__|___/");
        System.out.println("                                     |___/                     ");

        
        clearscreen();
        while(!checker){
            System.out.println("Sign in as");
            System.out.println("1. Client");
            System.out.println("2. Receptionist");
            System.out.println("3. Manager");
            System.out.println("4. Exit");
        
            try{
            System.out.println("\nEnter your choice: ");
            int choice = scanner.nextInt();
            
                switch(choice){
                    case 1: Client.Client(args);
                    break;
                    case 2: Receptionist.Receptionist(args);
                    break;
                    case 3: Manager.Manager(args);
                    break;
                    case 4: clearscreen();
                            loadingscreen();
                            clearscreen();
                            System.out.println("Thank You. Come Again!!!");
                            System.exit(0);
                    default:
                        System.out.println("Invalid Choice. Please Try again");
                }
            }catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter your ID number.\n");
                    System.out.println("Type Anything to continue.");
                    scanner.nextLine();
                    scanner.next();
                    clearscreen();
            }
        }
    }
}