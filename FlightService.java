import java.sql.*;

class FlightService {
    public static String URL = "jdbc:mysql://localhost:3306/airline_management";
    public static String USER = "root";
    public static String PASSWORD = "";

    public boolean addFlight(String flightNumber, String source, String destination, String departureTime,
            String arrivalTime, int b_seats, int e_seats, int f_seats, String food, double b_price,
            double e_price, double f_price) {
        String query = "INSERT INTO flights (flight_number, source, destination, departure_time, arrival_time , f_food) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, flightNumber);
            stmt.setString(2, source);
            stmt.setString(3, destination);
            stmt.setString(4, departureTime);
            stmt.setString(5, arrivalTime);
            stmt.setString(6, food);
            int rowsInserted = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int flight_id = rs.getInt(1);
                Statement st = conn.createStatement();
                st.executeUpdate("insert into type_of_seats (flight_id,category,total_seat,avaible,price) values('"
                        + flight_id + "','economy','" + e_seats + "','" + e_seats + "','" + e_price + "')");
                st.executeUpdate("insert into type_of_seats (flight_id,category,total_seat,avaible,price) values('"
                        + flight_id + "','busniess','" + b_seats + "','" + b_seats + "','" + b_price + "')");
                st.executeUpdate("insert into type_of_seats (flight_id,category,total_seat,avaible,price) values('"
                        + flight_id + "','first','" + f_seats + "','" + f_seats + "','" + f_price + "')");

            }
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteFlight(int flightId) {
        String query = "DELETE FROM flights WHERE flight_id = ?";
        String query2 = "DELETE FROM type_of_seats WHERE flight_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();) {
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, flightId);
            stmt2.setInt(1, flightId);
            int rowsDeleted = stmt.executeUpdate() + stmt2.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateFlight(int flightId, String flightNumber, String source, String destination,
            String departureTime, String arrivalTime, double b_price, double f_price, double e_price) {

        try (Connection conn = DatabaseConnection.getConnection();) {
            String query = "UPDATE flights SET  source= ?, destination = ?, departure_time = ?, arrival_time = ? WHERE flight_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            Statement st = conn.createStatement();
            stmt.setString(1, source);
            stmt.setString(2, destination);
            stmt.setString(3, departureTime);
            stmt.setString(4, arrivalTime);
            stmt.setInt(5, flightId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                st.executeUpdate("update  type_of_seats set price='" + b_price + "'' where flight_id='" + flightId
                        + "'and category='busniess'");
                st.executeUpdate("update  type_of_seats set price='" + e_price + "' where flight_id='" + flightId
                        + "'and category='economic'");
                st.executeUpdate("update  type_of_seats set price='" + f_price + "' where flight_id='" + flightId
                        + "'and category='first'");
            }
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getFlightDetails(String source, String destination) {
        int flight_id = 0;
        String q1 = "select * from flights where source=? and destination=?";
        String q2 = "select category,avaible,price from type_of_seats where flight_id=?";
        try (Connection conn = DatabaseConnection.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(q1);
            PreparedStatement stmt2 = conn.prepareStatement(q2);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                flight_id = rs.getInt("flight_id");
                System.out.print("\t\tFlight Number: " + rs.getString("flight_number") + "\n\t\t" + "Source : "
                        + rs.getString("source") + "\n\t\t" + "Destination: " + rs.getString("destination") + "\n\t\t"
                        + "departure Time : " + rs.getString("departure_time") + "\n\t\t" + "Arrival Time :"
                        + rs.getString("arrival_time") + "\n");
                stmt2.setInt(1, flight_id);
                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()) {
                    System.out.println("\t\t" +
                            rs2.getString(1) + " Category : - \n\t\tAvaible Seats : " + rs2.getInt(2)
                            + "\n\t\tPrices : "
                            + rs2.getDouble(3));
                }
                System.out.println(
                        "-----------------------------------------------------------------------------------------------");

            }

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
