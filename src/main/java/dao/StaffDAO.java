package dao;

import entity.customer.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends AbstractGenericDao<Staff>{
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
