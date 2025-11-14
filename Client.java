package FinalProject;
import java.util.*;

public class Client {

    private String UserID;

    Client( String UserID){

        this.UserID = UserID;
    }

    public static void Client(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainCode.clearscreen();
        MainCode.loadingscreen();
        MainCode.clearscreen();

        System.out.println("What's your name?");
        String name = scanner.next();




    }
       
}