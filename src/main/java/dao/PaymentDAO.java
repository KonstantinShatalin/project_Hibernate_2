package dao;

import entity.customer.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends AbstractGenericDao<Payment>{
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
