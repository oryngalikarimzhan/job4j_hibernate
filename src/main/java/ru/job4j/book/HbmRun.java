package ru.job4j.book;

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
           /* Book one = Book.of("Изучаем Java");
            Book two = Book.of("java se 8 programmer i exam guide (exams 1z0-808)");
            Book three = Book.of("Паттерны проектирования");
            Book four = Book.of("Head First Servlets and JSP");
            Book five = Book.of("Badass: Making Users Awesome");

            Author first = Author.of("Кэти Сьерра");
            first.addBook(one);
            first.addBook(two);
            first.addBook(three);
            first.addBook(four);
            first.addBook(five);

            Author second = Author.of("Берт Бейтс");
            second.addBook(one);
            second.addBook(two);
            second.addBook(three);
            session.persist(first);
            session.persist(second);*/

            Author author = session.get(Author.class, 2);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}