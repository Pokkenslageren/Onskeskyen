package OnskeGrisen.Repository;

import OnskeGrisen.Model.User;
import java.sql.*;

import java.util.*;

public class UserRepository {

    ArrayList<User> users = new ArrayList<>();
    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "";
    Connection conn;

    public UserRepository() {

    }

    public void createUser(User user) {
       String query = "INSERT INTO users (user_name, user_password) VALUES (?, ?)";

       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection(database, dbUsername, dbPassword);
           if (conn == null) {
               System.out.println("connection not established.");
           }
           PreparedStatement pstmt = conn.prepareStatement(query);
           pstmt.setString(1, user.getUsername());
           pstmt.setString(2, user.getPassword());
           pstmt.executeUpdate(query);
           pstmt.close();

       } catch(Exception e) {
           System.out.println(e.getMessage());
        }
    }


    public User readUserByUsername(String userName) {
        String query = "SELECT * FROM users WHERE user_name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String readUserName = rs.getString("user_name");
                String readPassword = rs.getString("user_password");
                users.add(new User(readUserName, readPassword));
            }
            rs.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateUser(User user, String userName) {

    }

    public void deleteUser(String userName) {

    }

}
