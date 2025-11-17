package FinalProject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;



public class ClientLogin {

    String fullName;
    private String address;
    private String contactInfo;
    private String email;
    private int ClientLoginID;

    public ClientLogin(String fullName, String address, String contactInfo, String email) {
        this.fullName = fullName;
        this.address = address;
        this.contactInfo = contactInfo;
        this.email = email;
        this.ClientLoginID = generateClientLoginID(contactInfo);
    }


    public String getFirstName() {
        return this.fullName.split(" ")[0];
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getClientLoginID() {
        return this.ClientLoginID;
    }

    public static boolean continfoValidation(String ClientLoginCInfo) {
        Pattern patternCI = Pattern.compile("^0\\d{10}$");
        Matcher matcherCI = patternCI.matcher(ClientLoginCInfo);
        return matcherCI.matches();
    }

    public static boolean emailValidation(String ClientLoginEAdd) {
        Pattern patternEmail = Pattern.compile("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcherEmail = patternEmail.matcher(ClientLoginEAdd);
        return matcherEmail.matches();
    }

    public static int generateClientLoginID(String phone) {
        int sum = 0;
        int[] digits = new int[phone.length()];

        for (int j = 0; j < phone.length(); j++) {
            digits[j] = Character.getNumericValue(phone.charAt(j));
        }

        // sa mag dodocument, bubble sort po ito
        // converting the inputted phone number into ascending order of digits
        for (int j = 0; j < digits.length - 1; j++) {
            for (int k = 0; k < digits.length - 1 - j; k++) {
                if (digits[k] > digits[k + 1]) {
                    int temp = digits[k];
                    digits[k] = digits[k + 1];
                    digits[k + 1] = temp;
                }
            }
        }

        for (int j = 0; j < digits.length; j++) {
            sum += digits[j] * (j + 1);
        }

        return sum;
    }

    public static LinkedList<ClientLogin> loadClientLoginsFromFile() {
        LinkedList<ClientLogin> ClientLoginList = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ClientLogin.txt"))) {
            String line;
            String fullName = null;
            String email = null;
            int ClientLoginID = -1;

            while ((line = br.readLine()) != null) {

                if (line.startsWith("----- ClientLogin")) {
                    // Extract ClientLogin ID
                    String idStr = line.replace("----- ClientLogin", "").replace("-----", "").trim();
                    ClientLoginID = Integer.parseInt(idStr);
                }

                if (line.startsWith("Full Name:")) {
                    fullName = line.replace("Full Name:", "").trim();
                }

                if (line.startsWith("Email:")) {
                    email = line.replace("Email:", "").trim();
                }

                // When ending line is reached → add ClientLogin
                if (line.startsWith("----------------------------------------")) {
                    if (fullName != null && email != null && ClientLoginID != -1) {
                        ClientLogin c = new ClientLogin(fullName, "", "", email);
                        c.ClientLoginID = ClientLoginID;
                        ClientLoginList.add(c);
                    }

                    // Reset for next ClientLogin
                    fullName = null;
                    email = null;
                    ClientLoginID = -1;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading ClientLogin.txt");
        }

        return ClientLoginList;
    }

    public static ClientLogin ClientLogin(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fullName, address, contactInfo, email;
        boolean isValid;

        MainCode.clearscreen();
        MainCode.loadingscreen();
        MainCode.clearscreen();

        while (true) {
            MainCode.clearscreen();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║             Client Login ACTION MENU             ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1. Already have an account - Log in             ║");
            System.out.println("║  2. Register a new account                       ║");
            System.out.println("║  3. Go back                                      ║");
            System.out.println("╚══════════════════════════════════════════════════╝\n");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    MainCode.clearscreen();
                    LinkedList<ClientLogin> ClientLoginList = loadClientLoginsFromFile();
                    ClientLogin loggedIn = null;

                    while (loggedIn == null) {
                        loggedIn = loginClientLogin(ClientLoginList);
                        if (loggedIn == null) {
                            System.out.println("Press Enter to try again or type 'back' to return to menu...");
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("back")) {
                                break;
                            }
                        }
                    }

                    if (loggedIn != null) {

                        ClientReservation.ClientReservation(null);
                        return loggedIn;
                    }
                    break;

                case "2":

                    MainCode.clearscreen();
                    System.out.print("Client Login Full Name (ex.: John D. Doe): \n");
                    fullName = sc.nextLine();

                    System.out.print("\nClient Login Address (ex.: Mataas na Lupa, Lipa City, Batangas): \n");
                    address = sc.nextLine();

                    while (true) {
                        System.out.print("\nClient Login Contact Information (ex.: 09123456789): \n");
                        contactInfo = sc.nextLine();
                        isValid = continfoValidation(contactInfo);
                        if (!isValid) {
                            System.out.println("\nInvalid Contact Information. Please try again. \n");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.print("\nClient Login Email Address (ex.: john.doe@example.com): \n");
                        email = sc.nextLine();
                        isValid = emailValidation(email);
                        if (!isValid) {
                            System.out.println("\nInvalid Email Address. Please try again. \n");
                        } else {
                            break;
                        }
                    }

                    ClientLogin newClientLogin = new ClientLogin(fullName, address, contactInfo, email);
                    int ClientLoginID = newClientLogin.getClientLoginID();
                    String firstname = newClientLogin.getFirstName();

                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateTimeRegistered = now.format(formatter);

                    MainCode.clearscreen();
                    MainCode.verifyingscreen();
                    MainCode.clearscreen();

                    System.out.println("\nSuccessful Registration!!! Congratulations " + firstname + "!");
                    System.out.println("Here is your ClientLogin ID: " + ClientLoginID);
                    System.out.println("Please do not share it with anyone");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ClientLogin.txt", true))) {
                        writer.write("----- ClientLogin " + ClientLoginID + " -----");
                        writer.newLine();
                        writer.write("Full Name: " + fullName);
                        writer.newLine();
                        writer.write("Email: " + email);
                        writer.newLine();
                        writer.write("Contact Number: " + contactInfo);
                        writer.newLine();
                        writer.write("Address: " + address);
                        writer.newLine();
                        writer.write("Date Registered: " + dateTimeRegistered);
                        writer.newLine();
                        writer.write("----------------------------------------\n");
                        writer.newLine();
                        System.out.println("ClientLogin information saved successfully.\n");
                    } catch (IOException e) {
                        System.out.println("Failed to save the ClientLogin information.");
                        e.printStackTrace();
                    }

                    System.out.println("Press Enter to return to the menu...");
                    sc.nextLine();
                    break;

                case "3":
                    MainCode.main(args);
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
            }
        }
    }

    public static ClientLogin loginClientLogin(LinkedList<ClientLogin> ClientLoginList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your login credentials:");

        System.out.print("ClientLogin Email Address: ");
        String emailInput = sc.nextLine();

        System.out.print("ClientLogin ID: ");
        int idInput = 0;

        while (true) {
            try {
                idInput = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ClientLogin ID:");
            }
        }

        for (ClientLogin c : ClientLoginList) {
            if (c.getClientLoginID() == idInput && c.getEmail().equalsIgnoreCase(emailInput)) {
                MainCode.clearscreen();
                MainCode.verifyingscreen();
                MainCode.clearscreen();
                System.out.println("Login successful! Welcome, " + c.getFirstName() + "!");
                return c;
            }
        }

        System.out.println("ClientLogin not found or credentials do not match. Please register first.");
        return null;
        
        
    }
}
