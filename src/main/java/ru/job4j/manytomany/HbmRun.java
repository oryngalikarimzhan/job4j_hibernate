package ru.job4j.manytomany;

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
            Address one = Address.of("Kazanskaya", "1");
            Address two = Address.of("Piterskaya", "10");
            Address three = Address.of("aaa", "10");

            Person first = Person.of("Nikolay");
            first.addAddress(one);
            first.addAddress(two);
            first.addAddress(three);
            System.out.println(first + "\n\n\n\n\n\n");
            Person second = Person.of("Anatoliy");
            second.addAddress(two);

            session.persist(first);
            session.persist(second);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}