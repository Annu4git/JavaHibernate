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

        String[] cities = {"Delhi", "Delhi", "Mumbai", "Bharuch", "Delhi", "Kochi", "Delhi", "Chennai", "Delhi", "Agra"};

        for (int i = 1; i <= 10; i++) {
            UserDetails user = new UserDetails();
            user.setCity(cities[i-1]); user.setUsername("user_" + i);
            user.setUserId(i);
            session.save(user);
        }

        UserDetails exampleUser = new UserDetails();
        exampleUser.setCity("Delhi");

        Example example = Example.create(exampleUser);

        // Example example = Example.create(exampleUser).excludeProperty("propertyName");

        Criteria criteria1 = session.createCriteria(UserDetails.class)
                                    .add(example);

        List<UserDetails> userList1 = criteria1.list();
        for(UserDetails user : userList1) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
