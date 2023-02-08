package dao;

import entity.film.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends AbstractGenericDao<Actor>{

    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
