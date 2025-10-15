import Model.Client;
import Model.Produit;
import dao.DaoRepository;
import dao.DaoRepositoryImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DaoRepository<Integer,Client> clientDaoRepository=new DaoRepositoryImpl<>(Client.class);
        clientDaoRepository.findAll();
        clientDaoRepository.save(new Client());
    }
}