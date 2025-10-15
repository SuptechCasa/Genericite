package Model;

import annotations.Id;

public class Client {
    @Id
    int id;
    String nom;
    String prenom;
    String adresse;
    String telephone;
    String email;

    public Client(){}
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }
}
