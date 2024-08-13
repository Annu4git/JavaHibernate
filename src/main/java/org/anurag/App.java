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
        criteria1.add(Restrictions.eq("username", "user_4"));

        List<UserDetails> userList1 = criteria1.list();
        for(UserDetails user : userList1) {
            System.out.println(user);
        }

        Criteria criteria2 = session.createCriteria(UserDetails.class);
        criteria2.add(Restrictions.gt("userId", 3))
                .add(Restrictions.le("userId", 8));

        List<UserDetails> userList2 = criteria2.list();
        for(UserDetails user : userList2) {
            System.out.println(user);
        }

        System.out.println("************ Between ************");
        Criteria criteria4 = session.createCriteria(UserDetails.class);
        criteria4.add(Restrictions.between("userId", 3, 5));

        List<UserDetails> userList4 = criteria4.list();
        for(UserDetails user : userList4) {
            System.out.println(user);
        }

        System.out.println("************ Like ************");
        Criteria criteria3 = session.createCriteria(UserDetails.class);
        criteria3.add(Restrictions.like("username", "%9%"));

        List<UserDetails> userList3 = criteria3.list();
        for(UserDetails user : userList3) {
            System.out.println(user);
        }

        System.out.println("************ OR ************");
        Criteria criteria5 = session.createCriteria(UserDetails.class);
        criteria5.add(Restrictions.or(Restrictions.like("username", "%2%"),
                Restrictions.between("userId", 7, 9)));

        List<UserDetails> userList5 = criteria5.list();
        for(UserDetails user : userList5) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
