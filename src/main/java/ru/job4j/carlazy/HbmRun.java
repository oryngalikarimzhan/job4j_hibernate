package ru.job4j.carlazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Brand toyota = Brand.of("Toyota");
            Model camry = Model.of("Camry", toyota);
            Model corolla = Model.of("Corolla", toyota);
            Model rav4 = Model.of("RAV4", toyota);
            Model landCruiser = Model.of("LandCruiser", toyota);
            Model yaris = Model.of("Yaris", toyota);
            session.persist(camry);
            session.persist(corolla);
            session.persist(rav4);
            session.persist(landCruiser);
            session.persist(yaris);

            list = session.createQuery("from Brand").list();
            for (Brand brand : list) {
                for (Model model : brand.getModels()) {
                    System.out.println(model);
                }
            }
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}