package org.anurag;

import org.anurag.dto.Address;
import org.anurag.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        UserDetails user1 = new UserDetails();
        user1.setUsername("Anurag");
        user1.setDate(new Date());

        List<Address> addressList = new ArrayList<>();
        Address homeAddress = new Address();
        homeAddress.setCity("Delhi");
        homeAddress.setPostcode("110032");
        addressList.add(homeAddress);

        Address officeAddress = new Address();
        officeAddress.setCity("Pune");
        officeAddress.setPostcode("511098");
        addressList.add(officeAddress);

        user1.setAddresses(addressList);

        session.beginTransaction();
        session.save(user1);
        session.getTransaction().commit();
        session.close();


        UserDetails user2 = null;

        session = sessionFactory.openSession();
        session.beginTransaction();

        user2 = session.get(UserDetails.class, 1);

        System.out.println("Fetch type Eager : " +
                "call to user address table is already made");

        System.out.println(user2.getAddresses());

        session.getTransaction().commit();
        session.close();


    }
}
