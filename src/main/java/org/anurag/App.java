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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

        Criteria criteria1 = session.createCriteria(UserDetails.class);
        criteria1.add(Restrictions.lt("userId", 5))
                .setProjection(Projections.property("username"));

        List<String> userList1 = criteria1.list();
        for(String user : userList1) {
            System.out.println(user);
        }

        System.out.println("Records in descending order : ");
        Criteria criteria2 = session.createCriteria(UserDetails.class)
                                    .addOrder(Order.desc("userId"));

        List<UserDetails> userList2 = criteria2.list();
        for(UserDetails user : userList2) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
