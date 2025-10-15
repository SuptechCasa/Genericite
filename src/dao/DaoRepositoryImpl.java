package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Bonjour à tout le monde
 * Fonction qui fait ....
 * @param <T>
 * @param <C>
 */
public class DaoRepositoryImpl<T,C> implements DaoRepository<T,C>{
    Class<C> entityClass;

    public DaoRepositoryImpl(Class<C> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public C findById(T id) {

        return null;
    }

    @Override
    public List<C> findAll() {
        String req="SELECT * from "+entityClass.getSimpleName()+" ";
        System.out.println("req:"+req);
        return List.of();
    }

    @Override
    public C save(C c) {
        List<String> chaines=new ArrayList<>();
        chaines.add("A");chaines.add("B");chaines.add("C");chaines.add("D");
        String.join(",",chaines);


        StringBuilder sql=new StringBuilder();
        //INSERT INTO Client(id, nom, prenom, adresse, telephone) VALUE (?,?,?,?,?);
        sql.append("INSERT INTO "+entityClass.getSimpleName()+" (");
        List<String> champs=new ArrayList<>();
        List<String> placeholders=new ArrayList<>();
        for(Field f  :  c.getClass().getDeclaredFields()){
            champs.add(f.getName());
            placeholders.add("?");
        }

        sql.append(String.join(",",champs)).append(") VALUES (").append(String.join(",",placeholders)).append(");");
        System.out.println(sql.toString());
        return null;
    }

    @Override
    public void delete(C c) {

    }
}
