package components;

public class Client {
    // 1.1.1 Creation of the client class
    
    // Attributes
    private static int nextClientNumber = 1;  // Variable estática para incrementar automáticamente el número de cliente
    private String name;
    private String firstName;
    private int clientNumber;

    // Constructor
    public Client(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
        this.clientNumber = nextClientNumber++;  // Asigna y luego incrementa el número de cliente
    }

    // Accessors and Mutators
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

    // No se proporciona un set para clientNumber porque se asigna automáticamente

    @Override
    public String toString() {
        return "Client [Name=" + name + ", First Name=" + firstName + ", Client Number=" + clientNumber + "]";
    }
}

