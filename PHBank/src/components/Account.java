package components;

public abstract class Account {

    protected static int nextAccountNumber = 1; 
    protected String label;
    protected double balance;
    protected int accountNumber;
    protected Client client;

    public Account(String label, Client client) {
        this.label = label;
        this.client = client;
        this.balance = 0;  
        this.accountNumber = nextAccountNumber++; 
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        this.balance += amount;  
    }

    public int getAccountNumber() {
        return accountNumber;
    }

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
    
    public void updateBalance(Flow flow) {
        if (this.accountNumber == flow.getTargetAccountNumber()) {
            if (flow instanceof Debit || flow instanceof Transfert) {
                this.balance -= flow.getAmount();
            } else if (flow instanceof Credit) {
                this.balance += flow.getAmount();
            }
        }
    }

}
