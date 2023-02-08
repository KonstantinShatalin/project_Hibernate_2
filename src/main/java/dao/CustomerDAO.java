package dao;

import entity.customer.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends AbstractGenericDao<Customer>{
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
