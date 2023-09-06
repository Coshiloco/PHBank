package components;

import java.util.Date;

public class Credit extends Flow {
    public Credit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, Date dateOfFlow) {
        super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
    }
}
