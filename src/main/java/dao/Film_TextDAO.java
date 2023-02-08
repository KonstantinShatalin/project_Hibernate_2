package dao;

import entity.film.Film_Text;
import org.hibernate.SessionFactory;

public class Film_TextDAO extends AbstractGenericDao<Film_Text>{
    public Film_TextDAO(SessionFactory sessionFactory) {
        super(Film_Text.class, sessionFactory);
    }
}
