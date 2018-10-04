package pl.sda.java9.database.daos;

import pl.sda.java9.database.DatabaseConector;
import pl.sda.java9.domains.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    public static User getUserByLogin(String login) {

        User user = null;

        try (Connection connection = DatabaseConector.getConnection()) {


            if (connection == null) {

                System.out.println("connection is null xdd");
            }

            PreparedStatement ps = connection.prepareStatement("select * from user where login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            user = createUserFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private static User createUserFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();

        while (rs.next()) {

            buildUser(rs, user);
        }

        return user;
    }

    private static void buildUser(ResultSet rs, User user) throws SQLException {

        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setIsAdmin(rs.getInt("isAdmin"));
    }

    public static boolean saveUser(User user) {

        boolean execute = false;



        try (Connection connection = DatabaseConector.getConnection()) {

            PreparedStatement ps = connection
                    .prepareStatement("insert into user(id, name, surname, login, password, isAdmin) VALUES(null , ?, ?, ?, ?, 0)");

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());

            execute = ps.execute();
            System.out.println(execute);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;
    }

    public static List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db user is null xdd mamamacka here again");
            }

            PreparedStatement ps = connection.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            users = returnUserList(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static List<User> returnUserList(ResultSet rs) throws SQLException {

        List<User> users = new ArrayList<>();
        while (rs.next()) {

            User user = new User();
            buildUser(rs, user);
            users.add(user);
        }
        return users;
    }

}
