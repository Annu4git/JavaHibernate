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

        String givenId = "5";
        Query selectQuery = session.createQuery("select username from users where userId > :givenId");
        selectQuery.setParameter("givenId", Integer.parseInt(givenId));
        List<String> users = selectQuery.list();
        for (String user : users) { System.out.println(user); }

        System.out.println("SQL Injection attack");
        String attackersId = "5 OR 1 = 1";
        selectQuery = session.createQuery("select username from users where userId > :attackersId");
        selectQuery.setParameter("attackersId", Integer.parseInt(attackersId));
        users = selectQuery.list();
        for (String user : users) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
