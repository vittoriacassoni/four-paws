package dao.bases;

import comuns.bases.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class DAO <E extends Entity> {
   protected Class<E> entityClass;

   public DAO(Class<E> entityClass) {this.entityClass = entityClass; }

   public abstract E select(String email) throws SQLException;
   public abstract ArrayList<E> list() throws SQLException;
   public abstract boolean insert(E entity) throws SQLException;

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
