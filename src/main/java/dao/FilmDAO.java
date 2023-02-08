package dao;

import entity.film.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends AbstractGenericDao<Film>{
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableFilmForRent() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f " +
                "where f.id not in(select distinct film_id.id from Inventory ) ", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
