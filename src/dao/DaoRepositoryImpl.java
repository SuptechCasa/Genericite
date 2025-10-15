package dao;

import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
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
    Connection connection;
    Statement stmt;
    public DaoRepositoryImpl(Class<C> entityClass) throws SQLException {
        this.entityClass = entityClass;
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase","root","");
        System.out.println("Connected to database");
        stmt=this.connection.createStatement();
    }

    @Override
    public C findById(T id) {

        return null;
    }

    @Override
    public List<C> findAll() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String req="SELECT * from "+entityClass.getSimpleName()+" ";

        ResultSet resultat=stmt.executeQuery(req);

        ResultSetMetaData metaData=resultat.getMetaData();

        int columnCount=metaData.getColumnCount();

        C instance=entityClass.getDeclaredConstructor().newInstance();

        List<C> list=new ArrayList<>();
        while(resultat.next()){
            System.out.println(resultat.getString("nom"));
            for(int i=1;i<=columnCount;i++){
               String columnName = metaData.getColumnName(i);
               String value = resultat.getString(i);
               // instance.getClass().getDeclaredMethod(columnName,String.class).invoke(instance,value);
            }


        }
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

    @Override
    public void deleteById(T id) {
        StringBuilder sql=new StringBuilder();
        sql.append("DELETE FROM "+entityClass.getSimpleName()+" WHERE "+getId()+"="+id);
    }

    public String getId(){
            for (Field declaredField : entityClass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                return declaredField.getName();
            }
        }
            return null;
    }
}
