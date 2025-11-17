package FinalProject;
import java.util.*;
import java.io.*;

public class ClientReservation {
    Scanner sc = new Scanner(System.in);

    //ArrayLists
    ArrayList<String> ReservationDate = new ArrayList<>();
    ArrayList<String> Facilitytype = new ArrayList<>();
    ArrayList<Integer> NumberofGuest = new ArrayList<>();
    ArrayList<Integer> NumberofFacilitytoReserve = new ArrayList<>();

    //array
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July","August","September","Ocotober","Novmber","December",};
    String[] facility = {"Single Room", "Double", "King", "Suite"};
    double[] pricePerUnit = {1500.00, 2000.00, 3000.00, 4000.00};
    int[] pax = {2, 3, 4, 6};

    //strings
    String reserveDate;
    String reserveFacility;
    String breakfast = "Free";
    String reservationRecord;

    // ints
    int year;
    int month;
    int day;
    int monthDays;
    int facilityType;
    int i;
    int numofGuest;
    int numofFacilitytoReserve;
    int addPerson;
    int additionalFee = 0;
    int foodchoice = 0;
    int dinnerQuantity = 0;
    int dinnerTotal = 0;
    int lunchQuantity = 0;
    int lunchTotal = 0;
    int totalmealPrice = 0;
    
    //doubles
    Double lunch = 250.00;
    Double dinner = 350.00;
    Double payment = 0.00;
    Double kulang;
    Double partialFee;
    Double totalReservation = 0.00;
        
    //char
    char lunchdinner;
    char fullpayment;

    File reservFile = new File("RESERVE.txt");

    public ClientReservation(){
        
    }

    public boolean isLeapYear (int year){
        return (year % 4 == 0 && (year % 100 !=0 || year % 400 == 0));
    }

    public int getDaysInMonth(int month, int year){
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(month == 2 && isLeapYear(year)){
            return 29;
        }

        return days[month - 1];
    }

   public void getcreateFile(File reserFile){
        try {
            if (reserFile.createNewFile()) {
                System.out.println("RESERVE text file created successfully.");
            } else {
                System.out.println("RESERVE text file already exist.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create the file.");
            e.printStackTrace();
        }
   }


       public void getReservationDate() {
            System.out.println("Reservation Date");
            while (true) {
                try {
                    System.out.print("Please select a month (1 = January - 12 = December): ");
                    month = sc.nextInt();
                    if (month >= 1 && month <= 12) {
                        break;
                    }
                    System.out.println("Invalid month.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer for the month.");
                    sc.nextLine();
                }
            }
    
            System.out.print("Please select year (ex: 2025): ");
            year = sc.nextInt();
    
            monthDays = getDaysInMonth(month, year);
            System.out.println(monthNames[month - 1] + " has " + monthDays + " days.");
    
            while (true) {
                try {
                    System.out.print("Please select a day (1-" + monthDays + "): ");
                    day = sc.nextInt();
                    if (day >= 1 && day <= monthDays) break;
                    System.out.println("Invalid day.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer for the day.");
                    sc.nextLine();
                }
            }
    
            reserveDate = monthNames[month - 1] + " " + day + ", " + year;
            System.out.println("You selected: " + reserveDate);
            ReservationDate.add(reserveDate);
        }

    public void getFacility() {
        System.out.println("\nFacility Options:");
        System.out.println("    Facility        Price Per Unit     Maximum # of Pax");
        System.out.println("-----------------------------------------------------------");
        for(int i=0; i<facility.length; i++){
            System.out.printf("%-3d %-17s %-23.2f %d%n", i + 1, facility[i], pricePerUnit[i], pax[i]);
        }

        
        while (true) {
            try {
                System.out.print("Choose facility type: ");
                facilityType = sc.nextInt();
                if (facilityType >= 1 && facilityType <= 4) {
                    break;
                }
                System.out.println("Invalid choice.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for the day.");
                sc.nextLine();
            }
        }

        i = facilityType - 1;
        reserveFacility = facility[i];
        Facilitytype.add(reserveFacility);


        while (true) {
            try {
                System.out.print("Number of guest (REMEMBER: There is an additional Php500.00 for every extra person for room accommodation): " );
                numofGuest = sc.nextInt();

                if(numofGuest > pax[i]){
                    addPerson = numofGuest - pax[i];
                    additionalFee = addPerson * 500;

                    System.out.println("You added: " + addPerson + " pax");
                    System.out.println("Your additioanl fee is: Php" + additionalFee);
                }
                System.out.println();
                NumberofGuest.add(numofGuest);
                break;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid integer for the day.");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Number of Faclities to Rerve: ");
                numofFacilitytoReserve = sc.nextInt();
                
                System.out.println();
                NumberofFacilitytoReserve.add(numofFacilitytoReserve);
                break;
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid integer for the day.");
                sc.nextLine();
            }
        }
    }

    public void getMeals() {
        System.out.println("    Breakfast        Lunch         Dinner");
        System.out.println("----------------------------------------------");
        System.out.printf("%10s %15.2f %13.2f%n", breakfast, lunch, dinner);

        while (true) {
            try {
                System.out.print("Would like to avail Lunch and Dinner? (Y/N) ");
                lunchdinner = sc.next().toUpperCase().charAt(0);

                if(lunchdinner == 'Y'){
                    do {
                        System.out.println("Select 1 to input Lunch quantity");
                        System.out.println("Select 2 to input Dinner quantity");
                        System.out.println("Select 0 to view Total");
                        foodchoice = sc.nextInt();
                    switch (foodchoice) {
                        case 1:
                            System.out.print("Meal Lunch Quantity: "); 
                            lunchQuantity = sc.nextInt();
                            lunchTotal = lunchQuantity * 250;
                            break;

                        case 2: 
                            System.out.print("Meal Dinner Quantity: "); 
                            dinnerQuantity = sc.nextInt();
                            dinnerTotal = dinnerQuantity * 250;
                            break;
                        
                        default:
                            System.out.println("Invalid choice. Please select 1, 2, or 0.");
                            break;
                    }
                    } while (foodchoice != 0);
                
                } else if (lunchdinner == 'N') {
                    lunchTotal = 0;
                    dinnerTotal = 0;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                    continue;
                }
            
                totalmealPrice = lunchTotal + dinnerTotal;
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid character.");
                sc.nextLine();
            }
        }
    }
    
    public void getPayment() {
        System.out.println("\nReservation Summary:");
        System.out.println("----------------------------");
        System.out.println("Reservation Date: " + ReservationDate.get(0));
        System.out.println("Facility Type: " + Facilitytype.get(0));
        System.out.println("Number of Guests: " + NumberofGuest.get(0));

        if (additionalFee > 0) {
            System.out.println("Additional Fee for Extra Pax: Php " + additionalFee);
        } else {
            System.out.println("No Additional Fee for Extra Pax.");
        }

        System.out.println("Total Meal Price: Php " + totalmealPrice);
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Cost for Reservation: Php " + (pricePerUnit[i] * NumberofFacilitytoReserve.get(0) + additionalFee + totalmealPrice));
        System.out.println("------------------------------------------------------------");
        System.out.println("Thank you for your reservation! We look forward to serving you.");

        System.out.println("You have the option to pay 30% reservation fee or pay the full amount.");
        totalReservation = (pricePerUnit[i] * NumberofFacilitytoReserve.get(0) + additionalFee + totalmealPrice);
        Double totalReservation = (pricePerUnit[i] * NumberofFacilitytoReserve.get(0) + additionalFee + totalmealPrice);

        System.out.println("Total Reservation Amount: " + totalReservation);
            while (true) {
                try {
                    System.out.print("Would you be able to pay for everything now? (Y/N): ");
                    fullpayment = sc.next().toUpperCase().charAt(0);

                    if (fullpayment == 'Y') {
                        System.out.println("Total Reservation Amount: " + totalReservation);
                        System.out.print("Input your payment: P");
                        payment = sc.nextDouble();

                        while (payment < totalReservation) {
                            kulang = totalReservation - payment;
                            System.out.println("Insufficient payment (Php " + kulang + ")");
                            System.out.print("Input your reservation payment: Php");
                            payment += sc.nextDouble();
                        }
                    } else if (fullpayment == 'N') {
                        partialFee = totalReservation * 0.30;
                        System.out.println("30% Reservation fee: Php " + partialFee);
                        System.out.print("Input your reservation payment: P");
                        payment = sc.nextDouble();

                        while (payment < partialFee) {
                            kulang = partialFee - payment;
                            System.out.println("Insufficient payment (Php " + kulang + ")");
                            System.out.print("Input your payment: Php");
                            payment += sc.nextDouble();
                        }
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid character or number.");
                    sc.nextLine();
            } 
        }
    }

    public void saveToFile() {
        try (BufferedWriter reserveWriter = new BufferedWriter(new FileWriter(reservFile, true))) {
            reserveWriter.write("Date: " + reserveDate);
            reserveWriter.newLine();
            reserveWriter.write("Facility: " + reserveFacility);
            reserveWriter.newLine();
            reserveWriter.write("Guests: " + NumberofGuest.get(0));
            reserveWriter.newLine();
            reserveWriter.write("Additional Fee: " + additionalFee);
            reserveWriter.newLine();
            reserveWriter.write("Meals Total: " + (totalmealPrice));
            reserveWriter.newLine();
            reserveWriter.write("Total Reservation: " + String.valueOf(totalReservation));
            reserveWriter.newLine();
            reserveWriter.write("Payment Made: " + payment);
            reserveWriter.newLine();
            reserveWriter.write("--------------------------------------------------\n");
            reserveWriter.newLine();
        } catch (IOException e) {
            System.out.println("Failed to write to the file.");
            e.printStackTrace();
        }
    }

    public void getReservation(){
        getReservationDate();
        getFacility();
        getMeals();
        getPayment();
        getcreateFile(reservFile);
        saveToFile();
    }

    public static void ClientReservation(String[] args) {
        ClientReservation reservation = new ClientReservation();
        reservation.getReservation();
    }

}