package dao;

import entity.address.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends AbstractGenericDao<Address>{
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
