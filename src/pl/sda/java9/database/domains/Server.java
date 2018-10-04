package pl.sda.java9.database.domains;

public class Server {

    private Integer id;

    private String name;
    private String host;
    private Integer port;
    private Integer owner;
    private String status;

    public Server(Integer id, String name, String host, Integer port, Integer owner, String status) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.port = port;
        this.owner = owner;
        this.status = status;
    }

    public Server() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
