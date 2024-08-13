package org.anurag;

import org.anurag.dto.FourWheeler;
import org.anurag.dto.TwoWheeler;
import org.anurag.dto.UserDetails;
import org.anurag.dto.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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

        Query selectQuery = session.createQuery("from users");

        List list = selectQuery.list();
        System.out.println("Total users : " + list.size());

        selectQuery = session.createQuery("from users where userId > 5");
        list = selectQuery.list();
        System.out.println("users with userId > 5 : " + list.size());

        session.getTransaction().commit();
        session.close();

    }
}
