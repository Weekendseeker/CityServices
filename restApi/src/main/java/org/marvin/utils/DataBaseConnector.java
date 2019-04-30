package org.marvin.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseConnector {

    private SessionFactory sessionFactory;


    protected Session openSession(){

        Session session = sessionFactory.openSession();

        if( !session.isConnected() || !session.isOpen() || session == null) return null;

        return session;

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
