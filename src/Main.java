import Model.Client;
import Model.Produit;
import dao.DaoRepository;
import dao.DaoRepositoryImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        DaoRepository<Integer,Client> clientDaoRepository=new DaoRepositoryImpl<>(Client.class);
        for (Client client : clientDaoRepository.findAll()) {
            System.out.println(client);
        }
        clientDaoRepository.save(new Client(4,"HAKIMI","Achraf","Rue 4","06786448","HAKIMI@gmail.com"));
    }
}