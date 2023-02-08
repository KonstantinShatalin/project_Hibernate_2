package dao;

import entity.customer.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractGenericDao<Rental>{
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyUnreturnedRental() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.return_date is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
