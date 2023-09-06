package components;

public class Client {

    private static int nextClientNumber = 1;
    private String name;
    private String firstName;
    private int clientNumber;

    public Client(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
        this.clientNumber = nextClientNumber++; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    @Override
    public String toString() {
        return "Client [Name=" + name + ", First Name=" + firstName + ", Client Number=" + clientNumber + "]";
    }
}

