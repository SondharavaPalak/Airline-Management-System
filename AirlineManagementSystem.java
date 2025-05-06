
import java.sql.*;
import java.util.*;

class DatabaseConnection {

    public static String URL = "jdbc:mysql://localhost:3306/airline_management";
    public static String USER = "root";
    public static String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
}

class AirlineManagementSystem {

    public static Scanner sc = new Scanner(System.in);
    public static UserService objuserService = new UserService();
    public static FlightService objflightService = new FlightService();
    public static BookingService objbookingService = new BookingService();
    public static EmployeeService objemployeeService = new EmployeeService();
    public static int flightId;

    public static void main(String[] args) throws Exception {
        while (true) {
            try {

                System.out
                        .println(
                                "-----------------------------------------------------------------------------------");
                System.out.println("\t\tWelcome to the Chak De India Airline Management System");
                System.out.println("\t\t1. Admin Login");
                System.out.println("\t\t2. User Login");
                System.out.println("\t\t3. User Registration");
                System.out.println("\t\t4. for Employee login");
                System.out.println("\t\t5. Exit");
                System.out.println(
                        "--------------------------------------------------------------------------------------");
                System.out.print("\t\tEnter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        userLogin();
                        break;
                    case 3:
                        userRegistration();
                        break;
                    case 4:
                        empLogin();
                        break;
                    case 5:
                        System.out.println("\t\tExiting...");
                        return;
                    default:
                        System.out.println("\t\tInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\tInput Mismatched Enter Valid choice");
                sc.next();
            }
        }
    }

    public static void adminLogin() {

        System.out.print("\t\tEnter username: ");
        String username = sc.nextLine();
        System.out.print("\t\tEnter password: ");
        String password = sc.nextLine();
        if (objuserService.adminlogin(username, password)) {
            System.out.println("\t\tAdmin logged in successfully.");
            adminMenu();
        } else {
            System.out.println("\t\tInvalid credentials. Please try again.");
        }
    }

    public static void userLogin() throws Exception {

        System.out.print("\t\tEnter username: ");
        String username = sc.nextLine();
        System.out.print("\t\tEnter password: ");
        String password = sc.nextLine();

        if (objuserService.loginUser(username, password)) {
            System.out.println("\t\tUser logged in successfully.");
            userMenu();
        } else {
            System.out.println("\t\tInvalid credentials. Please try again.");
        }
    }

    public static void empLogin() throws Exception {

        System.out.print("\t\tEnter username: ");
        String username = sc.nextLine();
        System.out.print("\t\tEnter password: ");
        String password = sc.nextLine();

        if (objuserService.loginemp(username, password)) {
            System.out.println("\t\tEmployee logged in successfully.");
            employee_menu();
        } else {
            System.out.println("\t\tInvalid credentials. Please try again.");
        }
    }

    public static void userRegistration() {
        System.out.print("\t\tEnter username: ");
        String username = sc.nextLine();
        System.out.print("\t\tEnter password: ");
        String password = sc.nextLine();
        System.out.print("\t\tEnter the your age: ");
        int age = sc.nextInt();
        System.out.print("\t\tEnter your gender (male or female): ");
        String gender = sc.next();
        System.out.print("\t\tEnter the phone number: ");
        String phno = sc.next();
        System.out.print("\t\tEnter the email: ");
        String email = sc.next();
        sc.nextLine();
        if (objuserService.registerUser(username, password, age, gender, phno, email)) {
            System.out.println("\t\tUser registered successfully.");
        } else {
            System.out.println("\t\tRegistration failed. Please try again.");
        }
    }

    public static void adminMenu() {
        while (true) {
            try {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("\t\tAdmin Menu:");
                System.out.println("\t\t1. Add Employee");
                System.out.println("\t\t2. Delete Employee");
                System.out.println("\t\t3. Update Employee");
                System.out.println("\t\t4. View All Employees");
                System.out.println("\t\t5. Logout");
                System.out.println(
                        "------------------------------------------------------------------------------------");
                System.out.print("\t\tEnter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addEmployee(objemployeeService);
                        break;
                    case 2:
                        deleteEmployee(objemployeeService);
                        break;
                    case 3:
                        updateEmployee(objemployeeService);
                        break;
                    case 4:
                        viewAllEmployees(objemployeeService);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("\t\tInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\tInput mismatched Enter valid choice");
                sc.next();
            }
        }

    }

    public static void employee_menu() throws Exception {
        while (true) {
            try {
                System.out
                        .println("-----------------------------------------------------------------------------------");
                System.out.println("\t\tEmployee Menu:");
                System.out.println("\t\t1. Add Flight");
                System.out.println("\t\t2. Delete Flight");
                System.out.println("\t\t3. Update Flight");
                System.out.println("\t\t4. Check Flight Details");
                System.out.println("\t\t5. Logout");
                System.out.println(
                        "--------------------------------------------------------------------------------------");
                System.out.print("\t\tEnter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addFlight();
                        break;
                    case 2:
                        deleteFlight();
                        break;
                    case 3:
                        updateFlight();
                        break;
                    case 4:
                        checkFlightDetails();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("\t\tInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\tInput mismatched Enter valid choice");
                sc.next();
            }
        }

    }

    public static void userMenu() throws Exception {
        while (true) {
            try {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("\t\tUser Menu:");
                System.out.println("\t\t1. Check Flight Details");
                System.out.println("\t\t2. Book Flight");
                System.out.println("\t\t3. Cancel Flight");
                System.out.println("\t\t4. Logout");
                System.out
                        .println("----------------------------------------------------------------------------------");
                System.out.print("\t\tEnter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        checkFlightDetails();
                        break;
                    case 2:
                        bookFlight();
                        break;
                    case 3:
                        cancelFlight();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("\t\tInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\tInput mismatched Enter valid choice");
                sc.next();
            }
        }
    }

    public static void addFlight() {
        try {

            System.out.print("\t\tEnter flight number: ");
            String flightNumber = sc.nextLine();
            System.out.print("\t\tEnter source: ");
            String source = sc.nextLine();
            System.out.print("\t\tEnter destination: ");
            String destination = sc.nextLine();
            System.out.print("\t\tEnter departure time (YYYY-MM-DD HH:MM:SS): ");
            String departureTime = sc.nextLine();
            System.out.print("\t\tEnter arrival time (YYYY-MM-DD HH:MM:SS): ");
            String arrivalTime = sc.nextLine();
            System.out.print("\t\tEnter number of seats of busniess class: ");
            int b_seats = sc.nextInt();
            System.out.print("\t\tEnter the price of busniess class ticket: ");
            double b_price = sc.nextDouble();
            System.out.print("\t\tEnter number of seats of First seats: ");
            int f_seats = sc.nextInt();
            System.out.println("\t\tEnter the price of first ticket: ");
            double f_price = sc.nextDouble();
            System.out.print("\t\tEnter number of seats of economy class: ");
            int e_seats = sc.nextInt();
            System.out.println("\t\tEnter the price of ecomomy class ticket: ");
            double e_price = sc.nextDouble();
            sc.nextLine(); // consume newline
            System.out.print("\t\tEnter food options (veg or non veg): ");
            String food = sc.nextLine();

            if (objflightService.addFlight(flightNumber, source, destination, departureTime, arrivalTime,
                    b_seats, e_seats, f_seats, food, b_price, e_price, f_price)) {
                System.out.println("\t\tFlight added successfully.");
            } else {
                System.out.println("\t\tFailed to add flight. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void deleteFlight() {
        try {

            System.out.print("\t\tEnter flight ID: ");
            int flightId = sc.nextInt();
            sc.nextLine(); // consume newline
            if (objflightService.deleteFlight(flightId)) {
                System.out.println("\t\tFlight deleted successfully.");
            } else {
                System.out.println("\t\tFailed to delete flight. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void updateFlight() {
        try {

            System.out.print("\t\tEnter flight ID: ");
            int flightId = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("\t\tEnter old flight number: ");
            String flightNumber = sc.nextLine();
            System.out.print("\t\tEnter new source: ");
            String departure = sc.nextLine();
            System.out.print("\t\tEnter new destination: ");
            String arrival = sc.nextLine();
            System.out.print("\t\tEnter new departure time (YYYY-MM-DD HH:MM:SS): ");
            String departureTime = sc.nextLine();
            System.out.print("\t\tEnter new arrival time (YYYY-MM-DD HH:MM:SS): ");
            String arrivalTime = sc.nextLine();
            System.out.print("\t\tEnter the price of busniess class: ");
            double b_price = sc.nextDouble();
            System.out.print("\t\tEnter the price of economic class: ");
            double e_price = sc.nextDouble();
            System.out.print("\t\tEnter the price of busniess: ");
            double f_price = sc.nextDouble();
            sc.nextLine();

            if (objflightService.updateFlight(flightId, flightNumber, departure, arrival, departureTime, arrivalTime,
                    b_price, f_price, e_price)) {
                System.out.println("\t\tFlight updated successfully.");
            } else {
                System.out.println("\t\tFailed to update flight. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void checkFlightDetails() {
        System.out.print("\t\tEnter Source city: ");
        String source = sc.nextLine();
        System.out.print("\t\tEnter the destination city: ");
        String destination = sc.nextLine();
        // sc.nextLine(); // consume newline
        if (objflightService.getFlightDetails(source, destination)) {
            return;
        } else {
            System.out.println("\t\tFlight not found.");
        }
    }

    static int book_flight(String destination, String source) {
        String URL = "jdbc:mysql://localhost:3306/airline_management";
        String USER = "root";
        String PASSWORD = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM flights WHERE flights.destination = ? and flights.source = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, destination);
            pstmt.setString(2, source);
            rs = pstmt.executeQuery();

            List<Flight> availableFlights = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("flight_id"));
                flight.setSource(rs.getString("source"));
                flight.setDeatination(rs.getString("destination"));
                flight.setDepartureTime(rs.getString("departure_time"));
                flight.setArrivalTime(rs.getString("arrival_time"));
                flight.setFlightNumber(rs.getString("flight_number"));
                availableFlights.add(flight);
            }

            if (availableFlights.isEmpty()) {
                System.out.println("\t\tNo flights available for the entered destination.");
                return 0;

            }

            System.out.println("\t\tAvailable flights to " + destination + ":");
            System.out.println("----------------------------------------------------------------------------------");
            for (Flight flight : availableFlights) {
                ids.add(flight.getId());
                System.out.println("\t\tFlight ID: " + flight.getId() + " Source : " +
                        flight.getSource() +
                        ", Departure: " + flight.getDepartureTime() + ", Arrival: " +
                        flight.getArrivalTime() + "");
            }
            System.out.println("------------------------------------------------------------------------------------");
            while (true) {
                System.out.print("\t\tEnter the flight_id: ");
                flightId = sc.nextInt();
                if (ids.contains(flightId)) {
                    return 1;
                } else {
                    System.out.println("\t\tEnter the correct flight id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    public static void bookFlight() throws Exception {
        try {
            System.out.print("\t\tEnter user ID: ");
            int userId = sc.nextInt();
            System.out.print("\t\tEnter the source city: ");
            String source = sc.next();
            System.out.print("\t\tEnter the destination city: ");
            String destination = sc.next();
            if (book_flight(destination, source) != 0) {
                // System.out.print("\t\tEnter flight ID: ");
                // int flightId = sc.nextInt();
                System.out.print("\t\tEnter number of seats: ");
                int seatNumber = sc.nextInt();
                sc.nextLine(); // consume newline
                String flightClass = "";
                while (true) {
                    System.out.print("\t\tEnter class (economy, business, first): ");
                    flightClass = sc.nextLine();
                    if (flightClass.equalsIgnoreCase("economy") || flightClass.equalsIgnoreCase("business")
                            || flightClass.equalsIgnoreCase("first")) {
                        break;
                    } else {
                        System.out.println("Enter the correct category");
                    }
                }
                String food = "";
                while (true) {
                    System.out.print("\t\tEnter food options (veg or nonveg): ");
                    food = sc.nextLine();
                    if (food.equalsIgnoreCase("veg") || food.equalsIgnoreCase("nonveg")) {
                        break;
                    } else {
                        System.out.println("Enter the correct category");
                    }
                }

                if (objbookingService.bookFlight(userId, flightId, seatNumber, flightClass, food)) {
                    System.out.println("\t\tFlight booked successfully.");
                } else {
                    System.out.println("\t\tFailed to book flight. Please try again.");
                }
            } else {
                System.out.println("\t\tSorry No flight found");
            }

        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void cancelFlight() throws Exception {
        try {
            System.out.print("\t\tEnter booking ID: ");
            int bookingId = sc.nextInt();
            sc.nextLine(); // consume newline

            if (objbookingService.cancelFlight(bookingId)) {
                System.out.println("\t\tFlight canceled successfully.");
            } else {
                System.out.println("\t\tFailed to cancel flight. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void addEmployee(EmployeeService objemployeeService) {
        try {

            System.out.print("\t\tEnter employee Firstname: ");
            String fname = sc.nextLine();
            System.out.print("\t\tEnter employee last name: ");
            String lname = sc.nextLine();
            System.out.print("\t\tEnter position: ");
            String position = sc.nextLine();
            System.out.print("\t\tEnter salary: ");
            double salary = sc.nextDouble();
            String pass = randompass();
            sc.nextLine(); // consume newline

            if (objemployeeService.addEmployee(fname + " " + lname, position, salary, fname + "." + lname, pass)) {
                System.out.println("\t\tEmployee added successfully.");
                System.out.println("\t\tEmployee Username and password is : " + fname + "." + lname + " ::   " + pass);
            } else {
                System.out.println("\t\tFailed to add employee. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    static String randompass() {
        String string = "ABCCDEFG1234567890";
        String pass = "";
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * string.length());
            pass = pass + string.charAt(index);
        }
        return pass;
    }

    public static void deleteEmployee(EmployeeService objemployeeService) {
        try {

            System.out.print("\t\tEnter employee ID to delete: ");
            int employeeId = sc.nextInt();
            sc.nextLine(); // consume newline

            if (objemployeeService.deleteEmployee(employeeId)) {
                System.out.println("\t\tEmployee deleted successfully.");
            } else {
                System.out.println("\t\tFailed to delete employee. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void updateEmployee(EmployeeService objemployeeService) {
        try {

            System.out.print("\t\tEnter employee ID to update: ");
            int employeeId = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("\t\tEnter new employee full name: ");
            String name = sc.nextLine();
            System.out.print("\t\tEnter new position: ");
            String position = sc.nextLine();
            System.out.print("\t\tEnter new salary: ");
            double salary = sc.nextDouble();
            sc.nextLine(); // consume newline

            if (objemployeeService.updateEmployee(employeeId, name, position, salary)) {
                System.out.println("\t\tEmployee updated successfully.");
            } else {
                System.out.println("\t\tFailed to update employee. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\tInput mismatched Enter valid values");
            sc.next();
        }
    }

    public static void viewAllEmployees(EmployeeService objemployeeService) {
        objemployeeService.display_emp();
    }

}
