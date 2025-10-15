package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DaoRepository<T,C> {
    /**
     * C'est une fonction de recherche par Id
     * @param id C'est l'identifiant
     * @return On retourne un objet
     */
    public C findById(T id);
    public List<C> findAll() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public C save(C c) throws SQLException, InvocationTargetException, IllegalAccessException;
    public void delete(C c);
    public void deleteById(T id);
}
