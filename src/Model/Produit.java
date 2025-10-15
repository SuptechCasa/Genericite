package Model;

import annotations.Id;

public class Produit {
    @Id
    int id;
    String nom;
    String description;
    float prix;
}
