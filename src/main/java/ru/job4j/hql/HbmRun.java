package ru.job4j.hql;

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

            Candidate first = Candidate.of("Petr", 10, 300000);
            Candidate second = Candidate.of("Oryngali", 1, 50000);
            Candidate third = Candidate.of("Stas", 5, 250000);

            session.save(first);
            session.save(second);
            session.save(third);

            session.createQuery("from Candidate").list().forEach(System.out::println);

            System.out.println(session.createQuery("from Candidate where id = :id")
                    .setParameter("id", 1)
                    .uniqueResult()
            );
            System.out.println(session.createQuery("from Candidate where name = :name")
                    .setParameter("name", "Petr")
                    .uniqueResult()
            );
            session.createQuery(
                    "update Candidate c set c.salary = :salary, c.experience = :experience where id = :id")
                    .setParameter("salary", 350000)
                    .setParameter("experience", 13)
                    .setParameter("id", 1)
                    .executeUpdate();
            session.createQuery("delete from Candidate where id = :id")
                    .setParameter("id", 3)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}