package ru.job4j.car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model camry = Model.of("Camry");
            Model corolla = Model.of("Corolla");
            Model rav4 = Model.of("RAV4");
            Model landCruiser = Model.of("LandCruiser");
            Model yaris = Model.of("Yaris");
            session.save(camry);
            session.save(corolla);
            session.save(rav4);
            session.save(landCruiser);
            session.save(yaris);

            Brand toyota = Brand.of("Toyota");
            toyota.addModel(session.load(Model.class, 1));
            toyota.addModel(session.load(Model.class, 2));
            toyota.addModel(session.load(Model.class, 3));
            toyota.addModel(session.load(Model.class, 4));
            toyota.addModel(session.load(Model.class, 5));

            session.save(toyota);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}