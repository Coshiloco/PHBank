package components;

import java.util.Date;

public class Transfert extends Flow {
    private int sourceAccountNumber;

    public Transfert(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, Date dateOfFlow, int sourceAccountNumber) {
        super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(int sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }
}
