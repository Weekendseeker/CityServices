package org.marvin.utils;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseConnector {

    private SessionFactory sessionFactory;
    private List<Session> sessionsList;
    {
        sessionsList = new ArrayList<>();
    }

    protected Session openSession(){


        Session session = sessionFactory.openSession();
        System.out.println();

        if( !session.isConnected() || !session.isOpen() || session == null)throw new SessionException("Opps try to late");


        sessionsList.add(session);

        return session;

    }

    protected void closeSession(Session session){

        if(!session.isOpen()) return;

        session.close();
        sessionsList.remove(session);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
