package models;

public class Client extends User {
    private int idClient;


    public Client() {
    }

    public Client(int id) {
        super();
        this.idClient = id;
    }

    public int getIdClient() {

        return idClient;
    }

    public void setId(int id) {

        this.idClient = id;
    }

    public Client getClient(Client c) {
        return c;
    }
}
