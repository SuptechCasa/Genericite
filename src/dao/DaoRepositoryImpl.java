package dao;

import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    PreparedStatement pstmt;
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
            for(int i=1;i<=columnCount;i++){
               String columnName = metaData.getColumnName(i);
               Object value = resultat.getObject(i);
               setProperty(columnName,instance,value);
            }
            list.add(instance);
        }
        return list;
    }

    @Override
    public C save(C c) throws SQLException, InvocationTargetException, IllegalAccessException {
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

        pstmt=this.connection.prepareStatement(sql.toString());
        int i=1;

        for(Field f  :  c.getClass().getDeclaredFields()){
            f.setAccessible(true);
            pstmt.setObject(i,f.get(c));
            i++;
        }

        pstmt.executeUpdate();
        pstmt.close();

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

    public void setProperty(String columnName,C instance,Object value) throws InvocationTargetException, IllegalAccessException {
        String methodName="set"+columnName.substring(0, 1).toUpperCase()+columnName.substring(1).toLowerCase();
        System.out.println(methodName);
        for (Method m : entityClass.getDeclaredMethods()) {
            m.setAccessible(true);
            if (m.getName().equals(methodName)) {
                m.invoke(instance,value);
            }
        }
    }
    public Object getProperty(String columnName,C instance) throws InvocationTargetException, IllegalAccessException {
        String methodName="get"+columnName.substring(0, 1).toUpperCase()+columnName.substring(1).toLowerCase();
        for (Method m : entityClass.getDeclaredMethods()) {
            m.setAccessible(true);
            if (m.getName().equals(methodName)) {
                return m.invoke(instance);
            }
        }
        return null;
    }
}
