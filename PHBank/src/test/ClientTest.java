package test;  // Paquete diferente al de la clase Client

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientTest {

    public static void main(String[] args) {
        List<Client> clients = loadClients(3);  // Cargamos 3 clientes para la prueba
        displayClients(clients);
        
        List<Account> accounts = loadAccounts(clients);
        displayAccounts(accounts);
    }

    /**
     * Método para cargar una lista de clientes.
     * @param numOfClients número de clientes que se quieren generar.
     * @return una lista con el número especificado de clientes.
     */
    public static List<Client> loadClients(int numOfClients) {
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= numOfClients; i++) {
            clients.add(new Client("name" + i, "firstname" + i));
        }
        return clients;
    }
    
    /**
     * Metodo para cargar cuentas basadas en una lista de clientes
     * Crea una cuenta de ahorros y una cuenta corriente para cada cliente
     * @param clientes lista de clientes
     * @return una lista de cuentas
     */
    public static List<Account> loadAccounts(List<Client> clients) {
    	List<Account> accounts = new ArrayList<Account>();
    	for (Client client : clients) {
			accounts.add(new CurrentAccount("Current Account", client));
			accounts.add(new SavingsAccount("Savings Account", client));
		}
    	return accounts;
    }

    /**
     * Método para mostrar la lista de clientes.
     * @param clients lista de clientes que se quieren mostrar.
     */
    public static void displayClients(List<Client> clients) {
        String clientStrings = clients.stream()
                                      .map(Client::toString)
                                      .collect(Collectors.joining("\n"));
        System.out.println(clientStrings);
    }
    
    /**
     * Metodo para mostrar la lista de cuentas
     * @param accounts lista de cuentas que se quieran mostrar 
     */
    public static void displayAccounts(List<Account> accounts) {
    	String accountStrings = accounts.stream()
    			.map(Account::toString)
    			.collect(Collectors.joining("\n"));
    	System.out.println(accountStrings);
    }
}

