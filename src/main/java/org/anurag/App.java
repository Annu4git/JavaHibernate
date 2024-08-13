package org.anurag;

import org.anurag.dto.FourWheeler;
import org.anurag.dto.TwoWheeler;
import org.anurag.dto.UserDetails;
import org.anurag.dto.Vehicle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails u1 = session.get(UserDetails.class, 2);
        u1.setUsername("updated username");

        // will not fetch again, since 1st level cache is there by default
        u1 = session.get(UserDetails.class, 2);

        session.getTransaction().commit();
        session.close();


    }
}




/*

session = sessionFactory.openSession();
        session.beginTransaction();

        // this will fetch again, since it is a different session
        // for this 2nd level cache must be enabled
        System.out.println("This will fire select query, since new session : "+ session.get(UserDetails.class, 2));

        session.getTransaction().commit();
        session.close();
 */
