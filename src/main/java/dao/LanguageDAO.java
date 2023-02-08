package dao;

import entity.film.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends AbstractGenericDao<Language>{
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
