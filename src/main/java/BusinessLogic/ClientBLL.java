package BusinessLogic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.ClientAgeValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;
import Model.Client;
/**
 * Business logic class for managing clients.
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;
    /**
     * Constructs a new instance of the ClientBLL class.
     * Initializes validators and clientDAO.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        this.clientDAO = new ClientDAO();
    }
    /**
     * Finds a client by their ID.
     *
     * @param id the ID of the client
     * @return the found client
     */
    public Client findClientById(int id) {
        Client st= (Client) this.clientDAO.findById(id);
        return (Client) st;

    }
    /**
     * Finds a client by their name.
     *
     * @param nume the name of the client
     * @return the found client
     * @throws NoSuchElementException if the client is not found
     */
    public Client findClientByName(String nume) {
        Client st= (Client) this.clientDAO.findByName(nume,"nume");
        if (st == null) {
            throw new NoSuchElementException("The Client with nume =" + nume + " was not found!");
        }
        return (Client) st;

    }
    /**
     * Inserts a new client.
     *
     * @param client the client to insert
     * @return the inserted client's ID
     */
    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        int c=this.clientDAO.insert( client);
        if(c==1)
        {
            System.out.println("inserarea s+a realizat cu succes");
            afisareAllClient();
        }
        else System.out.println("nu s-a putut insera");
        return (int) c;
    }
    /**
     * Deletes a client by their ID.
     *
     * @param id the ID of the client to delete
     */
    public void deleteClient(int id){
        this.clientDAO.delete(id);
        afisareAllClient();
    }
    /**
     * Updates a client.
     *
     * @param client the client to update
     * @return the updated client
     */
    public Client updateClient(Client client)
    {
        Client c=this.clientDAO.update(client);
        afisareAllClient();
        return c;
    }
    /**
     * Displays all clients.
     */
    public void afisareAllClient()
    {
        List<Client> list = new LinkedList<Client>();
        list=findAllClient();
        for (int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).toString());
        }

    }
    /**
     * Retrieves data of all clients.
     *
     * @return a 2D array containing the client data
     */
    public Object[][] getDataClients()
    {
        List<Client> list = new LinkedList<Client>();
        list=findAllClient();
        Object[][] data=null;
        data= new Object[list.size()][5];
        for(int i=0;i<list.size();i++)
        {
            Client c= list.get(i);
            data[i][0]=c.getId();
            data[i][1]=c.getNume();
            data[i][2]=c.getAddress();
            data[i][3]=c.getEmail();
            data[i][4]=c.getAge();
        }
        return data;
    }
    /**
     * Retrieves the names of the client fields.
     *
     * @return an array of field names
     */
    public String[] getFildNames()
    {
        Field[] fields= ClientDAO.class.getDeclaredFields();
        String[] fieldsName= new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldsName[i] = fields[i].getName();
        }
        return fieldsName;
    }
    /**
     * Retrieves the names of all clients.
     *
     * @return a list of client names
     */
    public List<String> getClientNames() {
        List<String> names = new ArrayList<>();
        List<Client> clients = findAllClient();
        for (Client client : clients) {
            names.add(client.getNume());
        }
        return names;
    }
    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public List<Client> findAllClient()
    {
        return this.clientDAO.findAll();
    }
}
