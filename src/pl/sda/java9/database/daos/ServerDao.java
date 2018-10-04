package pl.sda.java9.database.daos;

import pl.sda.java9.database.DatabaseConector;
import pl.sda.java9.database.domains.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerDao {

    public static Server getServerById(Integer id) {
        Server server = null;

        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db server is null xdd mamamacka here");
            }

            PreparedStatement ps = connection.prepareStatement("select * from server where id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            server = createServerFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return server;
    }

    private static Server createServerFromResultSet(ResultSet rs) throws SQLException {

        Server server = new Server();

        while (rs.next()) {

            bulidServer(rs, server);
        }

        return server;
    }


    public static List<Server> getAllServers() {

        List<Server> servers = new ArrayList<>();


        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db server is null xdd mamamacka here again");
            }

            PreparedStatement ps = connection.prepareStatement("select * from server");
            ResultSet rs = ps.executeQuery();


            servers = returnServersList(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servers;
    }

    private static List<Server> returnServersList(ResultSet rs) throws SQLException {

        List<Server> servers = new ArrayList<>();
        while (rs.next()) {

            Server server = new Server();
            bulidServer(rs, server);
            servers.add(server);
        }
        return servers;
    }

    private static void bulidServer(ResultSet rs, Server server) throws SQLException {
        server.setId(rs.getInt("id"));
        server.setName(rs.getString("name"));
        server.setHost(rs.getString("host"));
        server.setPort(rs.getInt("port"));
        server.setOwner(rs.getInt("owner"));
        server.setStatus(rs.getString("status"));
    }


    public static boolean saveOrUpdateServer(Server server) {

        PreparedStatement ps = null;
        boolean execute = false;
        try (Connection connection = DatabaseConector.getConnection()) {

            if (server.getId() == null) {
                ps = connection.prepareStatement("insert into server(id, name, host, port, owner, status) VALUES (null , ?, ?, ?, ?, ?)");

            } else {
                ps = connection.prepareStatement("update server set name = ?, host = ?, port = ?, owner = ?, status = ? where id = ?");
                ps.setInt(6, server.getId());
            }

            ps.setString(1, server.getName());
            ps.setString(2, server.getHost());
            ps.setInt(3, server.getPort());
            ps.setInt(4, server.getOwner());
            ps.setString(5, server.getStatus());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;
    }


    public static void saveServer(Server server) {

        PreparedStatement ps = null;
        try (Connection connection = DatabaseConector.getConnection()) {

            ps = connection.prepareStatement("insert into server(id, name, host, port, owner, status) VALUES (null , ?, ?, ?, ?, ?)");

            ps.setString(1, server.getName());
            ps.setString(2, server.getHost());
            ps.setInt(3, server.getPort());
            ps.setInt(4, server.getOwner());
            ps.setString(5, server.getStatus());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateServer(Server server){

        PreparedStatement ps = null;
        try (Connection connection = DatabaseConector.getConnection()) {

            ps = connection.prepareStatement("update server set name = ?, host = ?, port = ?, owner = ?, status = ? where id = ?");

            ps.setString(1, server.getName());
            ps.setString(2, server.getHost());
            ps.setInt(3, server.getPort());
            ps.setInt(4, server.getOwner());
            ps.setString(5, server.getStatus());
            ps.setInt(6, server.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
