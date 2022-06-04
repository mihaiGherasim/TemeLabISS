package persistence;

import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Query;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements Repository{

    static SessionFactory sessionFactory;

    static void initialize(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            System.err.println("Exception:"+e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

    public List<Book> findAll() {
        initialize();
        List<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> result = session.createQuery("from Book", Book.class).list();
            for (Book book : result) {
                books.add(book);
            }
            session.getTransaction().commit();
        }
        close();
        return books;
    }

    public Book findById(int id) {
        initialize();
        Book result = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Book> query = session.createQuery("from Book where isbn=:isbn", Book.class);
            query.setParameter("isbn", id);
            result = query.uniqueResult();
        }
        return result;
    }

    public void updateBooks(int id, String newNumber) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Book book = session.load(Book.class, id);
                book.setNrExemplare(book.getNrExemplare()-Integer.parseInt(newNumber));
                session.update(book);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addBooks(String id, String cantitate) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Book book = session.load(Book.class, Integer.parseInt(id));
                book.setNrExemplare(book.getNrExemplare()+Integer.parseInt(cantitate));
                session.update(book);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addBook(Book book) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        }
    }
}
