package test; 

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class ClientTest {

    public static void main(String[] args) {
        List<Client> clients = loadClients(3);
        displayClients(clients);
        
        List<Account> accountList = loadAccounts(clients);
        Hashtable<Integer, Account> accountTable = convertToHashtable(accountList);
        displayAccountsTable(accountTable);
    }

    public static List<Client> loadClients(int numOfClients) {
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= numOfClients; i++) {
            clients.add(new Client("name" + i, "firstname" + i));
        }
        return clients;
    }

    public static List<Account> loadAccounts(List<Client> clients) {
    	List<Account> accounts = new ArrayList<Account>();
    	for (Client client : clients) {
			accounts.add(new CurrentAccount("Current Account", client));
			accounts.add(new SavingsAccount("Savings Account", client));
		}
    	return accounts;
    }
    
    public static Hashtable<Integer, Account> convertToHashtable(List<Account> accounts) {
        Hashtable<Integer, Account> table = new Hashtable<>();
        for (Account account : accounts) {
            table.put(account.getAccountNumber(), account);
        }
        return table;
    }

    public static void displayClients(List<Client> clients) {
        String clientStrings = clients.stream()
                                      .map(Client::toString)
                                      .collect(Collectors.joining("\n"));
        System.out.println(clientStrings);
    }
    
    public static void displayAccountsTable(Hashtable<Integer, Account> accountsTable) {
        String accountStrings = accountsTable.values().stream()
            .sorted((a1, a2) -> Double.compare(a1.getBalance(), a2.getBalance()))
            .map(Account::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(accountStrings);
    }
}

