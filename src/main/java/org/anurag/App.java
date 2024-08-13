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

        Query query = session.getNamedQuery("getUserById");
        query.setParameter("id", 1);
        UserDetails user1 = (UserDetails) query.uniqueResult();
        System.out.println(user1.getUsername());

        query = session.getNamedNativeQuery("getUserByName");
        query.setParameter("name", "user_2");
        UserDetails user2 = (UserDetails) query.uniqueResult();
        System.out.println(user2.getUserId());

        session.getTransaction().commit();
        session.close();
    }
}
