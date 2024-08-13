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

        for(int i=1; i<10; i++) {
            UserDetails user1 = new UserDetails();
            user1.setUsername("user_"+i);
            session.save(user1);
        }
        session.getTransaction().commit();
        System.out.println("CREATE done");

        session.getTransaction().begin();
        UserDetails user2 = session.get(UserDetails.class, 4);
        System.out.println("READ : " + user2);

        user2.setUsername("Updated username");
        session.update(user2);
        UserDetails user3 = session.get(UserDetails.class, 4);
        System.out.println("UPDATE : " + user3);

        session.delete(user3);
        System.out.println("DELETE done");
        session.getTransaction().commit();
        session.close();
    }
}
