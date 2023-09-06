package test; 

import components.Account;
import components.AccountsWrapper;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClientTest {

    public static void main(String[] args) {
    	
    	System.out.println("<<<<<<< CLIENTS >>>>>>>>>");
    	
        List<Client> clients = loadClients(3);
        displayClients(clients);
        
        System.out.println("\n <<<<<<<<<<<<<<<<< ACCOUNTS >>>>>>>>>>>");
        
        List<Account> accountList = loadAccounts(clients);
        Hashtable<Integer, Account> accountTable = convertToHashtable(accountList);
        displayAccountsTable(accountTable);
        
        System.out.println("\n <<<<<<<<<<<<<<<<<<< FLOWS >>>>>>>>>>>>>>>>>");
        
        List<Flow> flows = loadFlows(accountTable);
        updateAccountBalances(accountTable, flows);
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
    
    public static List<Flow> loadFlowsFromJSON(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, Flow.class);
        try (var reader = Files.newBufferedReader(Paths.get(filePath))) {
            return mapper.readValue(reader, listType);
        }
    }
    

    public static List<Account> loadAccountsFromXML(String filePath) throws Exception {
        JAXBContext context = JAXBContext.newInstance(AccountsWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (var reader = Files.newBufferedReader(Paths.get(filePath))) {
            AccountsWrapper wrapper = (AccountsWrapper) unmarshaller.unmarshal(reader);
            return wrapper.getAccounts();
        }
    }
    
    public static List<Flow> loadFlows(Hashtable<Integer, Account> accountsTable) {
        List<Flow> flows = new ArrayList<>();

        Date currentDate = new Date();
        Date flowDate = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        flows.add(new Debit("Debit Flow", 1, 50, 1, true, flowDate));

        for (Account account : accountsTable.values()) {
            if (account instanceof CurrentAccount) {
                flows.add(new Credit("Credit Flow", flows.size() + 1, 100.50, account.getAccountNumber(), true, flowDate));
            } else if (account instanceof SavingsAccount) {
                flows.add(new Credit("Credit Flow", flows.size() + 1, 1500, account.getAccountNumber(), true, flowDate));
            }
        }

        flows.add(new Transfert("Transfer Flow", flows.size() + 1, 50, 2, true, flowDate, 1));

        return flows;
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
    
    public static void updateAccountBalances(Hashtable<Integer, Account> accountsTable, List<Flow> flows) {
        flows.forEach(flow -> {
            Account account = accountsTable.get(flow.getTargetAccountNumber());
            if (account != null) {
                account.updateBalance(flow);
            }

            if (flow instanceof Transfert) {
                account = accountsTable.get(((Transfert) flow).getSourceAccountNumber());
                if (account != null) {
                    account.updateBalance(flow);
                }
            }
        });

        Predicate<Account> negativeBalance = acc -> acc.getBalance() < 0;
        Optional<Account> accountWithNegativeBalance = accountsTable.values().stream().filter(negativeBalance).findAny();
        accountWithNegativeBalance.ifPresent(acc -> System.out.println("There is an account with a negative balance!"));
    }
    
}

