package test;  // Paquete diferente al de la clase Client

import components.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientTest {

    public static void main(String[] args) {
        List<Client> clients = loadClients(3);  // Cargamos 3 clientes para la prueba
        displayClients(clients);
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
     * Método para mostrar la lista de clientes.
     * @param clients lista de clientes que se quieren mostrar.
     */
    public static void displayClients(List<Client> clients) {
        String clientStrings = clients.stream()
                                      .map(Client::toString)
                                      .collect(Collectors.joining("\n"));
        System.out.println(clientStrings);
    }
}

