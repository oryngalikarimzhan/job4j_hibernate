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
            /*Vacancy firstVacancy = Vacancy.of(
                    "senior Java",
                    "we need senior Java developer",
                    5000,
                    "ex@hh.ru",
                    "87088602830"
            );
            Vacancy secondVacancy = Vacancy.of(
                    "senior Kotlin",
                    "we need senior Kotlin developer",
                    5000,
                    "ex@hh.ru",
                    "87088602830"
            );
            Vacancy thirdVacancy = Vacancy.of(
                    "senior Java",
                    "we need senior Java developer",
                    6000,
                    "xe@hh.ru",
                    "87087602830"
            );
            session.save(firstVacancy);
            session.save(secondVacancy);
            session.save(thirdVacancy);
            VacancyBase vacancyBase = VacancyBase.of("senior Java");
            vacancyBase.addVacancy(firstVacancy);
            vacancyBase.addVacancy(thirdVacancy);
            Candidate firstCandidate = Candidate.of("Stas", 5, 250000, vacancyBase);
            session.save(firstCandidate);*/

            System.out.println(session.createQuery("select distinct c from Candidate c "
                    + "join fetch c.vacancyBase vb "
                    + "join fetch vb.vacancyList "
                    + "where c.id = :id", Candidate.class)
                    .setParameter("id", 1)
                    .uniqueResult()
            );

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}