package components;

public abstract class Account {
    // 1.2.1 Creation of the account class

    // Attributes
    protected static int nextAccountNumber = 1;  // Variable estática para incrementar automáticamente el número de cuenta
    protected String label;
    protected double balance;
    protected int accountNumber;
    protected Client client;

    // Constructor
    public Account(String label, Client client) {
        this.label = label;
        this.client = client;
        this.balance = 0;  // El saldo inicial por defecto es 0
        this.accountNumber = nextAccountNumber++;  // Asigna y luego incrementa el número de cuenta
    }

    // Accessors and Mutators
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getBalance() {
        return balance;
    }

    // Este setter se actualizará más tarde según las instrucciones
    public void setBalance(double amount) {
        this.balance += amount;  // Por ahora, solo agregamos el monto al saldo
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // No se proporciona un set para accountNumber porque se asigna automáticamente
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account [Label=" + label + ", Balance=" + balance + ", Account Number=" + accountNumber + ", Client=" + client.toString() + "]";
    }
}
