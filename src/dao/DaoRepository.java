package dao;

import java.util.List;

public interface DaoRepository<T,C> {
    /**
     * C'est une fonction de recherche par Id
     * @param id C'est l'identifiant
     * @return On retourne un objet
     */
    public C findById(T id);
    public List<C> findAll();
    public C save(C c);
    public void delete(C c);

}
