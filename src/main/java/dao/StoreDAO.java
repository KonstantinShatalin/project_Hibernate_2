package dao;

import entity.customer.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends AbstractGenericDao<Store>{
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
