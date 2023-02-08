package dao;

import entity.customer.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends AbstractGenericDao<Inventory>{
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
