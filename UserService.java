import java.sql.*;

class UserService {
    public static String URL = "jdbc:mysql://localhost:3306/airline_management";
    public static String USER = "root";
    public static String PASSWORD = "";

    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE u_username = ? AND u_password = ?";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean loginemp(String username, String password) {
        String query = "SELECT * FROM employees WHERE e_username = ? AND e_password = ?";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String username, String password, int age, String gender, String phno, String email) {
        String query = "INSERT INTO users (u_username, u_password, u_age,u_gender,u_phone_no,u_email ) VALUES (?, ?, ?,?,?,?)";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, age);
            stmt.setString(4, gender);
            stmt.setString(5, phno);
            stmt.setString(6, email);
            int rowsInserted = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rowsInserted > 0) {
                System.out.println("--------------------------------------------------------------------------");
                if (rs.next()) {
                    System.out
                            .println("\t\tYour User id is : " + rs.getInt(1)
                                    + "Please Note It for Further Communication");
                    System.out.println("-----------------------------------------------------------------");
                }
            }
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    boolean adminlogin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}