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
        Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(FourWheeler.class)
                .addAnnotatedClass(TwoWheeler.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleName("Car");

        TwoWheeler bike = new TwoWheeler();
        bike.setVehicleName("Bike");
        bike.setSteeringHandle("Bike handle");

        FourWheeler car = new FourWheeler();
        car.setVehicleName("BMW");
        car.setSteeringWheel("Car steering wheel");

        session.beginTransaction();

        session.save(vehicle1);
        session.save(bike);
        session.save(car);

        session.getTransaction().commit();
        session.close();

    }
}
