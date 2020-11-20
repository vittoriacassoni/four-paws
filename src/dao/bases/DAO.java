package dao.bases;

import comuns.bases.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class DAO <E extends Entity> {
   protected Class<E> entityClass;

   public DAO(Class<E> entityClass) {this.entityClass = entityClass; }

   public abstract E select(String email) throws SQLException;
    public abstract E selectID(int id) throws SQLException;
   public abstract ArrayList<E> list() throws SQLException;
   public abstract boolean insert(E entity) throws SQLException;
   public abstract boolean update(E entity) throws SQLException;
   public abstract List<E> selectList(Integer userId) throws SQLException;
   public abstract List<E> selectAll() throws SQLException;

    protected E getInstanceOfE()
   {
       try
       {
           return entityClass.getDeclaredConstructor().newInstance();
       }
       catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e)
       {
           throw new RuntimeException();
       }
   }
}
