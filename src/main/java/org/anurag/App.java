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

        Query selectQuery = session.createQuery("select username from users");
        selectQuery.setFirstResult(5);  // skip 5 results
        selectQuery.setMaxResults(3);   // limit 3 records
        List<String> list = selectQuery.list();

        for (String user : list) {
            System.out.println(user);
        }

        selectQuery = session.createQuery("select count(*) from users");
        System.out.println("Total users : " + selectQuery.uniqueResult());

        session.getTransaction().commit();
        session.close();
    }
}
