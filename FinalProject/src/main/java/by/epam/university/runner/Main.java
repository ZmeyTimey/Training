package by.epam.university.runner;

import java.sql.*;

public class Main {

    private static final String DB_DRIVER_CLASS
            = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL
            = "jdbc:mysql://localhost:3306/selection_committee_test"
            + "?useUnicode=true&characterEncoding=UTF-8"
            + "&useSSL=false"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String ADD_USER_ROLE
            = "INSERT INTO `user_role` (`id`, `role`) VALUES (?, ?)";
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(DB_DRIVER_CLASS).newInstance();
            connection = DriverManager.getConnection(
                    DB_URL,DB_USER, DB_PASSWORD);
            statement = connection.prepareStatement(
                    ADD_USER_ROLE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 3);
            statement.setString(2, "LOX");
            statement.executeUpdate();

//            while (result.next()) {
//                System.out.println(result.getString("login"));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
