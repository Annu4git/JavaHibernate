package org.anurag;

import org.anurag.dto.FourWheeler;
import org.anurag.dto.TwoWheeler;
import org.anurag.dto.UserDetails;
import org.anurag.dto.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App
{
    public static void main( String[] args ) {
        Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails user1 = new UserDetails();
        user1.setUsername("Anurag");
        session.save(user1);

        session.getTransaction().commit();
        session.close();

        /* Updates may or may not happen after session is closed  */

        session = sessionFactory.openSession();
        session.getTransaction().begin();

        System.out.println(user1.getUsername());
        session.update(user1);
        /* Update will happend, since Hibernate does not know whether any updates took place
        *  when object was detached (not in session). Hibernate only knows objects who are Persistent (in session) */

        session.getTransaction().commit();
        session.close();


    }
}
