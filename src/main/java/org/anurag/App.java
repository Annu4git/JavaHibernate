package org.anurag;

import org.anurag.dto.Address;
import org.anurag.dto.UserDetails;
import org.anurag.dto.Vehicle;
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
        Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Vehicle.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        UserDetails user1 = new UserDetails();
        user1.setUsername("Anurag");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleName("Car");
        user1.getVehicle().add(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleName("Bike");
        user1.getVehicle().add(vehicle2);

        session.beginTransaction();
        session.save(user1);
        session.save(vehicle1);
        session.save(vehicle2);
        session.getTransaction().commit();
        session.close();

    }
}
